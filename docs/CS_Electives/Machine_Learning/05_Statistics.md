# Statistics

Statistical concepts such as Parameter estimation, Bias, Variance help in the aspects of generalization, over-fitting and under-fitting

IID: Independent & identically distributed

## Estimation Types

| Estimation Type | Regression Output                         | Classification Output                                                          |
| --------------- | ----------------------------------------- | ------------------------------------------------------------------------------ |
| Point           | $E(y \vert X)$                            | $E(c_i \vert X)$                                                               |
| Probabilistic   | $E(y \vert X)$<br />$\sigma^2(y \vert X)$ | $P(c_i \vert X)$<br />This is not the model confidence! This is the likelihood |

## Likelihood vs Confidence

- Likelihood is the probability of classification being of a certain class
  - Unreliable if input is unlike anything from training
- Confidence is the model’s confidence that the likelihood is correct

## IDK

|          | Expected deviation              |
| -------- | ------------------------------- |
| Bias     | from the true value             |
| Variance | caused by any particular sample |

## Function Estimation

Estimation of relationship b/w input & target variables, ie predict a variable $y$ given input $x$

$$
y = \hat y + \epsilon
$$

where $\epsilon$ is Bayes Error

## Statistical Learning Theory

Helps understand performance when we observe only the training set, through assumptions about the dataset

- Train & live data must have identical statistical properties
	- Training & test data arise from same process
	- Observations in each data set are independent
	- Training set and testing set are identically distributed
	- Techniques
		- Adversarial Validation/Domain Classifier
		- Anomaly detection
		- Data distributions
			- KL divergence
			- Jensen-Shannon
			- Kolmogorov-Smirnov test
			- Population Stability Index
- i.i.d
	- every observation is independent & identically distributed
- $X$ values are fixed in repeated sampling
- No specification bias
   - We need to use the correct functional form, which is theoretically consistent
- Distribution of $y \vert X$ is known
	- Distribution of $y \vert X$ can be any distribution, and the modelling should take that into account
	- You cannot assume normal distribution blindly
	- Distribution of $y$ does not matter
- No Unbiasedness
   - Independent vars should not be correlated with each other
   - If |correlation| > 0.5 between 2 independent vars, then we drop one of the variables
- High DOF
   - Degree of freedom $= n - k$, where
     - $n =$ number of observations
     - $k =$ no of independent variables
   - DOF $\to$ 0 leads to overfitting
- High coefficient of variation in $X$
   - We need more variation in values of $X$
   - Indian stock market is very volatile. But not in UAE; so it's hard to use it an independent var. Similarly, we cant use exchange rate in UAE as a regressor, as it is fixed to US dollars
- Groups are identical
	- if groups are different add indicator and interaction terms
- No variable interaction
	- Variable interaction: effect of $x_i$ on $y$ depends on $x_j$
	- Solution: add interaction terms
- No collinearity
- No [multicollinearity](#multicollinearity)
- Homoskedasticity
   - Constant variance
   - $\sigma^2 (y_i|x_i) = \text{constant}$ should be same $\forall i$
   - Check with
	   - Numerical
		   - rolling statistics (average, std)
	   - Graphical
		   - box plot with $x$ axis as $x_j$ and $y$ axis as $y$
		   - ridge plot with $y$ axis as $x_j$ and $x$ axis as $y$
		   - histogram with $x$ axis as $y$ and facet as $x_j$
   - [Causes of Heteroskedascity](#Causes-of-Heteroskedascity)
- There is no measurement error $\delta_i$ in $X$ or $Y$
   - $X_\text{measured} = X_\text{true}$
   - $y_\text{measured} = y_\text{true}$
   - $E(\delta_i)=0$
   - $\text{var}(\delta_i | x_i) = \sigma^2 (\delta_i|x_i) = \text{constant}$ should be same $\forall i$
   - $\text{Cov}(\delta_i, x_i) = 0, \text{Cov}(\delta_i, u_i) = 0$
	   - If there is measurement error, we need to perform [correction](#Errors-in-Measurement Correction)
- Stationarity
	- The statistical properties of the variables are bounded and remain constant over time
	- This applies to both inputs and output variables
- There is no autocorrelation
	- If there exists autocorrelation in time series, then we have to incorporate the lagged value of the dependent var as an explanatory var of itself
- For the Variance of distribution of potential outcomes, the range of distribution stays same over time
   - $\sigma^2 (x) = \sigma^2(x-\bar x)$:   else, the variable is **volatile**; hard to predict; **we cannot use OLS** and hence have to use weighted regression
   - if variance decreases, value of $y$ is more reliable as training data
   - if variance increases, value of $y$ is less reliable as training data
     - We use volatility modelling (calculating variance) to predict the pattern in variance

### Adversarial Validation

Create a new feature in the dataset as “Set”, which signifies if the data belongs to training/test set

Train a classifier to predict which set

ROC-AUC signifies how accurately the classifier can distinguish between the sets. Higher values $\ge 0.8$ imply that Train & Test data **not** from same distribution.

## Input Error

For higher order model, errors in $x$ will look like heteroskedasticity

### Attenuation Bias

High measurement error $\delta$ and random noise $u$ causes our estimated coefficients to be lower than the true coefficient

Hence, for straight line model, error in $x$  will bias the OLS estimate of slope towards zero
$$
\begin{aligned}
\lim_{n \to \infty} \hat \beta &= \beta \times \text{SNR} \\
\text{Signal-Noise Ratio: SNR} &= \dfrac{\sigma^2_x}{\sigma^2_x \textcolor{hotpink}{+ \sigma^2_u + \sigma^2_\delta}}
\end{aligned}
$$

### Errors-in-Measurement Correction

This can be applied to

- any learning algorithm
- for regressors or response variables(s)

Let’s say true values of a regressor variable $X_1$ was measured as $X_1^*$ with measurement error $\delta_1$, where $\delta_1 \ne N(0, 1)$. Here, we cannot ignore the error.

#### Step 1: Measurement Error

Use an appropriate distribution to model the measurement error. Not necessary that the error is random.

For eg, if we assume that the error is a skewed normal-distributed with variance $\sigma^2_{X_1}$ signifying the uncertainty.

$$
\delta_1 = N(\mu_{X_1}, \sigma^2_{X_1}, \text{Skew}_{X_1}, \text{Kurt}_{X_1})
$$

#### Step 2: Measurement

Model the relationship between the error and the measured value.

For eg, If we assume that the error is additive

$$
\begin{aligned}
X_1^* &= X_1 + \delta_1 \\
\implies X_1 &= X_1^* \textcolor{hotpink}{- \delta_1}
\end{aligned}
$$

#### Step 3: Model

Since $X_1^*$ is what we have, but we want the mapping with $X_1$,

$$
\begin{aligned}
\hat y &= f(X_1) \\
&= f(X_1^* \textcolor{hotpink}{- \delta_1})
\end{aligned}
$$

### Example

Example: Modelling with linear regression using a regressor with measurement error
$$
\begin{aligned}
\implies \hat y
&= \theta_0 + \theta_1 X_1 \\
&= \theta_0 + \theta_1 (X_1^* - \delta_1) \\
&= \theta_0 + \theta_1 X_1^* \textcolor{hotpink}{- \theta_1 \delta_1}
\end{aligned}
$$

## IIA

Independence of Irrelevant Alternatives

The IIA property is the result of assuming that errors are independent of each other in a classification task

The probability of $y = j$ relative to $y = k$ depends is not affected by the existence and the properties of other classes

$$
\begin{aligned}
p(y=j \vert x, z)
&= \dfrac{
\exp(\beta_j x + \textcolor{hotpink}{\gamma_j z})
}{
\sum_k^K \exp(\beta_k x + \textcolor{hotpink}{\gamma_k z} )
} \\
\implies p(y=j \vert x) 
&= \int \dfrac{
\exp(\beta_j x + \textcolor{hotpink}{\gamma_j z})
}{
\sum_k^K \exp(\beta_k x + \textcolor{hotpink}{\gamma_k z} )
} f(z) \cdot dz
\end{aligned}
$$
where $\gamma$ is the effect of the other classes (substitutes/complementary goods)

For IIA, $\gamma=0$

IIA property should be a desirable property for well-specified models

- the error for one alternative provides no information about the error for another alternative. This should be the property of a well-specified model such that the unobserved portion of utility is essentially “white noise.
- However, when a model omits important unobserved variables that explain individual choice patterns, however, the errors can become correlated over alternatives

## Heteroskedascity

### Causes of Heteroskedascity

- Misspecified model
- If output is $\bar y$, but the sample size is different for each calculated mean
  - $s_{\bar y} = \sigma_y/ \sqrt{n}$
  - Eg: Average income vs years of college
- Variance/standard error is relative to the $y$
  - Eg: Precision of tool is relative to the observed value, such as weighing scale
- Variance has been experimentally determined for each $y$ value
- Some distributions naturally have variance that is a function of the
  - Mean: Poisson
  - Mean & Variance: Gamma

### Statistical Test

- Sort residuals $u_i$ wrt corresponding $\vert y_i \vert$
- Divide residuals (esr for fits) into $g$ subgroups
- Test to see if sub-groups share same variance
  - $H_0:$ all groups have same variance

|                                      |                                                              | Distribution of statistic             | Null Hypothesis                    | Formula                                                      |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------- | ---------------------------------- | ------------------------------------------------------------ |
| Barlett                              | Assumes normal distribution (sensitive to deviations from normality) | $\chi^2$ distributed with $(g-1)$ DOF | $k$ sub-groups have equal variance | $\dfrac{(n-g) \ln s^2_\text{pool} - \sum\limits_{j=1}^g (n_j - 1) \ln s^2_j }{ 1 + \Big[ 1/[3(g-1)] \Big] \left[ \Big( \sum\limits_{j=1}^g \dfrac{1}{(n_j - 1)} \Big) - \dfrac{1}{n-g} \right] }$ |
| Brown-Forsythe/<br />Modified Levene | compares deviations from median; it is robust to deviations from normality, but has lower power<br /><br />$n_j > 25 \quad \forall j \in k$ | $t$ distribution with DOF = $n-g$     | Constant variance                  | $\dfrac{\vert \bar d_1 - \bar d_2 \vert}{s_\text{pool} \sqrt{\dfrac{1}{n_1} + \dfrac{1}{n_2}}}$<br />$d_{ij}=\vert x_{ij} - \text{med}_j \vert$ |
| White Test                           | Perform linear regression of $u_i^2$ with $x$ and test $nR^2$ as $X^2_{k-1}$ |                                       |                                    |                                                              |
| Breusch-Pagan                        | Variation of white test where $x$ is replaced with any variable of interest |                                       |                                    |                                                              |
| Park                                 | Perform linear regression of $\ln \vert u_i^2 \vert$ vs $\ln \vert x \vert$ and test significance of slope different from 0 |                                       |                                    |                                                              |

where

- $n=$ total number of data points
- $k=$ number of subgroups
- $n_j=$ sample size of $j$th sub-group
- $s^2_j =$ variance of $j$th sub-group
- $s^2_\text{pool} = \dfrac{1}{n-k} \sum\limits_{j=1}^k (n_j - 1) s^2_j$
- $\text{med}_j =$ median of $j$th sub-group

### Correcting

| Dependence of variance on $y_i$ | Solution                                       |
| ------------------------------- | ---------------------------------------------- |
| Known                           | Weighted regression                            |
|                                 | Data transformation                            |
| Unknown                         | GMM, generalized methods of moments estimation |

## Collinearity

2 variables are correlated

- Can be inspected through correlation matrix of 2 variables

### Implication

- Adding/removing predictor variables changes the estimated effect of the vars (for eg: regression coefficients)
- Standard errors of coefficients become larger
- Individual regression coefficients may not be significant, even if the overall model is significant
- Some regression coefficients may be significantly different than expected (even opposing sign)
- There can be multiple solutions for $\beta$
- Both variables will be insignificant if both are included in the regression model

- Dropping one will likely make the other significant

- Hence we can’t remove two (or more) supposedly insignificant predictors simultaneously: significance depends on what other predictors are included

### Causes

| Cause                           |                                                              |                                                              |
| ------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| No data: Inappropriate sampling | We only sample regions where predictors are correlated       | ![image-20240618225457951](./assets/image-20240618225457951.png){ loading=lazy } |
| Inappropriate model             | If range of predictors is small: $r(x, x^2) \ne 0$           |                                                              |
| True Population                 | Collinearity indeed exists in the true population (for eg, height and weight) |                                                              |

### Multicollinearity

Special case of Collinearity: Collinearity between 3 or more variables, regardless of whether or no no pair of variables are correlated

eg: $r(x_1, x_2) = r(x_1, x_3) = 0$, but $r(x_1, x_2+x_3) \ne 0$

### Detection

|                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| ------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Correlation matrix                         |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| Mutual Information Matrix                  |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| VIF<br />Variance Inflation Factor         | How much is the variance of the $k$th model coefficient **inflated** compared to case of no inflation<br><br>$\text{VIF}(x_j \vert x_j') = \dfrac{1}{1 - R^2_{x_j \vert x_{j'} }} ; \quad j' \in [0, k] - \{ j \}$<br><br>$R^2_{x_j \vert x_{j'}}$ is the **out-of-sample** $R^2$ when $x_j$ is regressed against all other predictor vars, using linear/non-linear model; out-of-sample is important to alleviate any issues of overfitting<br><br>- $1/\text{VIF}_{x_j \vert x_{j'}}=$ “tolerance”<br><br>- $\text{VIF}_{x_j \vert x_{j'}} = 1 \implies$ No collinearity between $x_j$ and other vars<br>- $\text{VIF}_{x_j \vert x_{j'}} \ge 4 \implies$ Investigate<br>- $\text{VIF}_{x_j \vert x_{j'}} \ge 10 \implies$ Act<br><br>$E[\text{VIF}_{x_j \vert x_{j'}}] \quad \forall j > 1 \implies$ Problematic |
| Eigensystem Analysis                       | Find eigenvalues of correlation matrix, ie $\tilde X^T \tilde X$<br /><br />If all eigenvalues are about the same magnitude, no multicollinearity<br />Else calculate condition number<br /><br />$\kappa = \lambda_\max/\lambda_\min$<br />If $\kappa > 100 \implies$ problem                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Tree-based Proximity with transposed input | Computationally-expensive?                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| Permutation feature similarity             | Features that show similar changes in importance when permuted together are considered similar<br><br>1. Train a tree-based model<br>3. Calculate score<br>3. For each feature i:<br>    - Permute its values<br>    - Calculate permuted score<br>    - For every other feature $j$:<br>      - Permute its values<br>      - Calculate doubly permuted score                                                                                                                                                                                                                                                                                                                                                                                                                                                      |

### Solution

- Derive theoretical constraints relating input vars: helps simplify model; can be linear/non-linear
- If we only care about prediction, restrict scope of model for interpolation only, ie new inputs should coincide with range of predictor vars that exhibit the same pattern of multicollinearity
- Drop problematic variables, ie ones with highest VIF
- Collect more data that breaks pattern of multicollinearity
- Measure coefficients in separate experiment (then fix those coefficients)
- Regularization: Even for perfect multicollinearity, the ridge regression solution will always exist
- PCA
	- Separates the high SE of coefficients from multicollinearity into components with low SE and high SE; you’d only include the low SE components
	- Helps identify unknown linear constraints
	- Limitation: cannot help with non-linear relationship
	
