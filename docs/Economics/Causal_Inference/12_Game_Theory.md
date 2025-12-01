# Game Theory

## Auction

Consider $n$ risk-neutral bidders, with independent private value $v_i ~ F$

Each bidder knows their own $v_i$ and distribution $F$, but not the $v_i$ of others

Observed winning bids are the Bayesian Nash equilibrium outcome of each game
$$
\begin{aligned}
b_i
&= v_i - \dfrac{1}{F(v_i)^{n-1}} \int \limits_0^{v_i} F(x)^{n-1} \cdot dx \\
&= v_i - \dfrac{1}{n-1} \dfrac{G_n(b_i)}{g_n(b_i)}
\end{aligned}
$$
where

- $b_i$ is the bid amount
- $g_n$ is pdf of bid distribution
- $G_n$ is cdf of bid distribution

There is no confounding between $N$ (the number of bidders) and $b_\max$ (the winning bid). Hence $f (N) ≡ E [b_\max|N]$non-parametrically identifies the effect of N on $b_\max$

The estimation problem is to learn $f (N)$ from data. Here, theory helps specify the functional form of $f (N)$ and therefore serves as a model selection mechanism

Theory also helps us to learn the values of the bidders – which cannot be identified nonparametrically – by specifying the functional form of the mapping from $\{ v_i \}$ to $\{ b_i \}$

Furthermore, structural modelling will allows us to obtain other things as well such as: 2nd-highest bid, etc

### Structural Estimation

1. For each auction, non-parametrically estimate $g_n$ and $G_n$ from observed bids $b_1, \dots, b_n$
2. For each bidder, calculate

$$
\hat v_i = b_i + \dfrac{1}{n-1} \dfrac{\hat G_n(b_i)}{\hat g_n(b_i)}
$$

3. Estimate $\hat F$ using $\hat v_i$
4. Predict winning bid

$$
\begin{aligned}
&E[\max \{ b_i \}] \\
&= E \Bigg[
\max \Big\{
v_i - \dfrac{1}{\hat F(v_i)^{n-1}}
\int\limits_0^{v_i} \hat F(x^{n-1}) \cdot dx
\Big\}
\Bigg]
\end{aligned}
$$

## Monopoly

In each market $m$ with population $N_m$ and mean income $I_m$, consumers choose between monopoly product and an outside good. Individual utilities are given by:

For each market $m$, given demand $q_m(p)$, the monopoly firm chooses $p$ that maximizes its revenue
$$
\begin{aligned}
u_{i0}^m &= \epsilon_{i0}^m \\
u_{i1}^m &= \beta_0 + \beta_1 I_m - \beta_2 p_m + \epsilon_{i1}^m \\
\pi_m &=
\sigma(\beta_0 + \beta_1 I_m - \beta_2 p_m)
\\
&=\dfrac{1}{1 + \dfrac{1}{\exp(\beta_0 + \beta_1 I_m - \beta_2 p_m)}} \\
p &= \max_p \{ p \times q_m(p) - c(q_m(p)) \} \\
c' (q_m) &= p_m + [q'_m (p_m)]^{-1} q_m
\end{aligned}
$$
where

- $(u_{i0}^m, u_{i1}^m)$ are indirect utilities of outside good and monopoly product resp
- $\epsilon_{ij}^m \sim \text{Gumbel}(0, 1)$
- $q_m \sim \text{Binomial}(N_m, \pi_m)$
- $c(q)$ is the monopoly firm’s cost function

Estimated marginal cost and demand curves for a market with median income and population

![Estimated marginal cost and demand curves for a market with median income and population](assets/image-20240420145244567.png){ loading=lazy }

Here, theory helps us to learn the marginal cost function of the monopoly firm as well as the consumer utility function – neither of which is observed and neither can be nonparametrically identified.

Using the estimation results, we can conduct welfare analysis and make normative statements: For example, calculating the total deadweight loss due to monopoly

