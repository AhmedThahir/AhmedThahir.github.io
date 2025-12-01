# Uncertainty

## Types of Uncertainty

| <span style="display:inline-block; text-align:right">Others’ knowledge</span><br /><br />Our knowledge | Known                                                     | Unknown                                                                  |
| ------------------------------------------------------------------------------------------------------ | --------------------------------------------------------- | ------------------------------------------------------------------------ |
| Known                                                                                                  | Things we are certain of                                  | We know there are things we can’t predict<br />eg: Random Process        |
| Unknown                                                                                                | Others know but you don’t know<br />eg: Insufficient data | Completely unexpected/unforeseeable events<br />eg: Unknown distribution |

|                                      | Epistemic                                           | Aleatoric                                      |
| ------------------------------------ | --------------------------------------------------- | ---------------------------------------------- |
| Uncertainty in                       | Model                                               | Data                                           |
| Cause                                | - Model misspecification<br>- Missing training data | - Measurement errors<br>- Process random noise |
| Reducible through more training data | ✅                                                   | ❌                                              |
| Can be learnt by model???            | ❌                                                   | ✅                                              |

## Uncertainty Quantification Methods

|                                | Concept                          | Assumption                                                                                            | Works for non-linear | Limitations                                                                                                                                                     |
| ------------------------------ | -------------------------------- | ----------------------------------------------------------------------------------------------------- | -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Asymptotic approach            | Central limit theorem            | - Assumes normal distribution of response residuals<br>- Assumes homoscedascity of response residuals | ❌                    | - Requires large sample size to satisfy asymptotic condition<br>- Requires appropriate formula for calculating standard error (not possible for complex models) |
| Bootstrapping<br />(preferred) | Random sampling with replacement |                                                                                                       | ✅                    | Higher computation cost                                                                                                                                         |
| Delta Approach                 |                                  |                                                                                                       | ✅                    |                                                                                                                                                                 |
| Conformal Prediction           |                                  |                                                                                                       |                      |                                                                                                                                                                 |

## Uncertainty Intervals

$$
\begin{aligned}
\{y_u, y_l\} &= \hat y \pm \Delta y
\end{aligned}
$$


|                      | $\Delta y$                                                      |
| -------------------- | --------------------------------------------------------------- |
| Normal Assumption    | $t_{n_\text{cal}, \alpha/2} \times \text{SE}$                   |
| Conformal Prediction | $S^{-1} \left[ q_{\frac{\lceil (n+1)\alpha \rceil}{n}} \right]$ |

### Normal Assumption

|                                                                             | Coefficient Confidence Interval                                                                                                                                 | Response Confidence Interval                                                                                                              | Response Prediction Interval                                                                                                                      |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| Notation                                                                    | $\sigma_{\hat \beta}$                                                                                                                                           | $\sigma \Big[ \hat \mu \vert x_{i, \text{new}} \Big]$                                                                                     | $\sigma \Big[ \hat y_{i, \text{new}} \vert x_{i, \text{new}} \Big]$                                                                               |
| The upper and lower bound for estimated __ at a given level of significance | $\hat \beta$                                                                                                                                                    | $\hat \mu \vert x_{i, \text{new}}$                                                                                                        | $\hat y \vert x_{i, \text{new}}$<br>$=\hat \mu \vert x_{i, \text{new}} + \hat u \vert x_{i, \text{new}}$                                          |
| Univariate Linear Regression<br>(Asymptotic Approach)                       | $\left\{ \text{RMSE} \sqrt{\dfrac{1}{n_\text{cal}} + \dfrac{\bar x^2}{n_\text{cal} \sigma^2_x}} , \dfrac{\text{RMSE}}{\sqrt{n_\text{cal} \sigma^2_x} }\right\}$ | $\text{RMSE} \times \sqrt{\dfrac{1}{n_\text{cal}} + \dfrac{(x_{i, \text{new}}- \bar x )^2}{n_\text{cal} \sigma_x^2}}$                     | $\text{RMSE} \times \sqrt{\dfrac{1}{n_\text{cal}} + \dfrac{(x_{i, \text{new}} - \bar x )^2}{n_\text{cal} \sigma_x^2} \ \textcolor{hotpink}{+ 1}}$ |
| Multivariate Linear Regression<br>(Asymptotic Approach)                     | ${\text{RMSE} \times \sqrt{\text{Cov}_{jj}}}$                                                                                                                   | $\text{RMSE} \times \sqrt{X_{i, \text{new}}^T \cdot \text{Cov} \cdot X_{i, \text{new}} }$                                                 | $\text{RMSE} \times \sqrt{X_{i, \text{new}}^T \cdot \text{Cov} \cdot X_{i, \text{new}}  \ \textcolor{hotpink}{+ 1}}$                              |
| Multivariate Non-Linear Regression<br>(Asymptotic + Delta Approach)         | ${\text{RMSE} \times \sqrt{\text{IF}_{jj}}}$                                                                                                                    | $\text{RMSE} \times \sqrt{ J_{i, \text{new}}^T \cdot \text{IF} \cdot J_{i, \text{new}} }$<br><br>![](assets/delta_method_uncertainty.png){ loading=lazy } | $\text{RMSE} \times  \sqrt{J_{i, \text{new}}^T \cdot \text{IF} \cdot J_{i, \text{new}}  \ \textcolor{hotpink}{+ 1} }$                             |

where
- $\text{Cov}$: Covariance matrix
	- $\text{Cov} = (X' X)^{-1}$
- $J$: Jacobean matrix
	- $J_{i, \text{new}} = \dfrac{\partial \hat y_{i, \text{new}}}{\partial \beta}$
- $H$: Hessian matrix
	- $H \approx (J^T J)$
- $\text{IF}:$ Inverse Fischer
	- $\text{IF} = H^{-1}$

High values for non-diagonal elements of $\text{Cov}_\beta$ means that the errors of $\beta$ are correlated with each other.

Degree of freedom $= n - k - 1$, where

- $n =$ sample size
- $k=$ no of input variables

Confidence and prediction intervals are narrowest at $X = \bar X$, and get wider further from this point.

![image-20231129140943148](./assets/image-20231129140943148.png){ loading=lazy }

Under homoskedasticity,
$$
\begin{aligned}
\hat V(\hat \beta)
&= (X' X)^{-1} \hat \sigma^2 \\
&=\dfrac{\hat \sigma^2}{\hat u_j' \hat u_j}
\end{aligned}
$$

### Note

- RMSE = RMSE of validation data
- If your validation error distribution is not normal, or you have a lot of data, you can use the quantiles of validation error distribution for the confidence intervals

### Intervals using Models’ Prediction

For each data point, take __ of multiple models

- average
- 5th quantile
- 95th quantile

## Predictive Density

Describes the full probabilistic distribution $\forall x$

![image-20240522140247341](./assets/image-20240522140247341.png){ loading=lazy }

## Trajectories/Scenarios

Equally-likely samples of multivariate predictive densities

![image-20240522140752649](./assets/image-20240522140752649.png){ loading=lazy }

## Uncertainty Propagation

| Function                    | Variance                                                                                                                                   |
| --------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| $aA$                        | $= a^2\sigma_A^2$                                                                                                                          |
| $aA + bB$                   | $= a^2\sigma_A^2 + b^2\sigma_B^2 + 2ab\,\text{Cov(A, B)}$                                                                                  |
| $aA - bB$                   | $= a^2\sigma_A^2 + b^2\sigma_B^2 - 2ab\,\text{Cov(A, B)}$                                                                                  |
| $AB$                        | $\approx f^2 \left[\left(\frac{\sigma_A}{A}\right)^2 + \left(\frac{\sigma_B}{B}\right)^2 + 2\frac{\text{Cov(A, B)}}{AB} \right]$           |
| $\frac{A}{B}$               | $\approx f^2 \left[\left(\frac{\sigma_A}{A}\right)^2 + \left(\frac{\sigma_B}{B}\right)^2 - 2\frac{\text{Cov(A, B)}}{AB} \right]$           |
| $\frac{A}{A+B}$             | $\approx \frac{f^2}{\left(A+B\right)^2} \left(\frac{B^2}{A^2}\sigma_A^2  +\sigma_B^2 - 2\frac{B}{A} \text{Cov(A, B)} \right)$              |
| $a A^{b}$                   | $\approx \left( {a}{b}{A}^{b-1}{\sigma_A} \right)^2 = \left( \frac{{f}{b}{\sigma_A}}{A} \right)^2$                                         |
| $a \ln(bA)$                 | $\approx \left(a \frac{\sigma_A}{A} \right)^2$[^4]                                                                                         |
| $a \log_{10}(bA)$           | $\approx \left(a \frac{\sigma_A}{A \ln(10)} \right)^2$[^5]                                                                                 |
| $a e^{bA}$                  | $\approx f^2 \left( b\sigma_A \right)^2$[^6]                                                                                               |
| $a^{bA}$                    | $\approx f^2 (b\ln(a)\sigma_A)^2$                                                                                                          |
| $a \sin(bA)$                | $\approx \left[ a b \cos(b A) \sigma_A \right]^2$                                                                                          |
| $a \cos \left( b A \right)$ | $\approx \left[ a b \sin(b A) \sigma_A \right]^2$                                                                                          |
| $a \tan \left( b A \right)$ | $\left[ a b \sec^2(b A) \sigma_A \right]^2$                                                                                                |
| $A^B$                       | $\approx f^2 \left[ \left( \frac{B}{A}\sigma_A \right)^2 +\left( \ln(A)\sigma_B \right)^2 + 2 \frac{B \ln(A)}{A} \text{Cov(A, B)} \right]$ |
| $\sqrt{aA^2 \pm bB^2}$      | $\approx \left(\frac{A}{f}\right)^2 a^2\sigma_A^2 + \left(\frac{B}{f}\right)^2 b^2\sigma_B^2 \pm 2ab\frac{AB}{f^2}\,\text{Cov(A, B)}$      |

For uncorrelated variables ($\rho_{AB}=0$, $\text{Cov(A, B)}=0$) expressions
for more complicated functions can be derived by combining simpler
functions. For example, repeated multiplication, assuming no
correlation, gives
$f = ABC; \qquad \left(\frac{\sigma_f}{f}\right)^2 \approx \left(\frac{\sigma_A}{A}\right)^2 + \left(\frac{\sigma_B}{B}\right)^2+ \left(\frac{\sigma_C}{C}\right)^2.$

For the case $f = AB$ we also have Goodman's expression[^7] for the
exact variance: for the uncorrelated case it is
$V(XY)= E(X)^2 V(Y) + E(Y)^2 V(X) + E((X-E(X))^2 (Y-E(Y))^2)$ and
therefore we have:
$\sigma_f^2 = A^2\sigma_B^2 + B^2\sigma_A^2 +  \sigma_A^2\sigma_B^2$

## Effect of correlation on differences

If *A* and *B* are uncorrelated, their difference *A-B* will have more
variance than either of them. An increasing positive correlation
($\rho_{AB}\to 1$) will decrease the variance of the difference,
converging to zero variance for perfectly correlated variables with the
[same variance](homoscedastic "wikilink"). On the other hand, a negative
correlation ($\rho_{AB}\to -1$) will further increase the variance of
the difference, compared to the uncorrelated case.

For example, the self-subtraction *f=A-A* has zero variance
$\sigma_f^2=0$ only if the variate is perfectly
[autocorrelated](autocorrelation "wikilink") ($\rho_A=1$). If *A* is
uncorrelated, $\rho_A=0$, then the output variance is twice the input
variance, $\sigma_f^2=2\sigma^2_A$. And if *A* is perfectly
anticorrelated, $\rho_A=-1$, then the input variance is quadrupled in
the output, $\sigma_f^2=4\sigma^2_A$ (notice $1-\rho_A=2$ for *f* = *aA*
− *aA* in the table above).

## Value at Risk Models

- Derive the risk profile of the firm
- Protect firm against unacceptably large concentrations
- Quantify potential losses

![image-20240203155439617](./assets/image-20240203155439617.png){ loading=lazy }

1. Collect data
2. Graph the data to inspect data quality
3. Transform prices data into returns form (percentage diff of prices)
4. Look at the frequency distribution
5. Obtain the standard deviation (volatility)
6. Multiply volatility with one-sided $Z_1$ to estimate 99% worst-case loss

## Classification

$$
[\text{Bin}(n, p)_{1-\alpha/2}, \text{Bin}(n, p)_{\alpha/2}]
$$

![](assets/Wilson%20score%20interval.png){ loading=lazy }

