# Random Variables

## Types of Random Numbers

|               | Can be produced by computers | Easy to implement |
| ------------- | ---------------------------- | ----------------- |
| Truly Random  | ❌                            | ❌                 |
| Quasi-Random  | ✅                            | ❌                 |
| Pseudo-Random | ✅                            | ✅                 |

## Random Distribution Functions

|      |                              |
| ---- | ---------------------------- |
| PDF  | Probability Density Function |
| CDF  | Cumulative Density Function  |

## Central Limit Theorem

PDF of sample mean with sample size $n>30$ tends to normal distribution, regardless of what the underlying distribution is

$$
\bar x \sim N \left(\mu, \frac{\sigma^2}{n} \right)
$$

Interpretation: Given a sufficiently large sample

1. Mean of sample means $\approx$ normal-distribution
2. Mean of sample means $\approx$ population mean
3. Variance of sample means $\approx$ Population Variance/Sample Size

## Moment-Generating Function

For a random variable $x$
$$
\begin{aligned}
M_x(t) &= E[ e^{tx} ] \\
\implies \underbrace{\dfrac{d^{(k)} M_x}{dt^{(k)}} (0)}_{\text{k th derivative } } &= \underbrace{E(x^k)}_{\text{k th moment}} \\
\implies M_x(t) &= \sum_{k=0}^\infty \dfrac{t^k}{k!} m_k, & m_k = E(x^k) \\
t &\in R, k \in Z
\end{aligned}
$$
Note: Does not exist for all distributions (for eg: Log-Normal)
$$
\begin{aligned}
x, y \text{ have same dist} &\iff M_x(t) = M_y(t) \\
x, y \text{ have same dist} &\implies {m_k}_x = {m_k}_y & \text{(Converse not necessarily true)}
\end{aligned}
$$
For a sequence of random variables $x_1, x_2, \dots, $

$$
M_{X_i}(t) \to M_X(t) \implies P(X_i \le x) \to P(X \le x)
$$

## Large of Large Numbers

Consider iid rv $x_1, \dots, x_n$ with mean and variance $\mu, \sigma^2$
$$
x = \dfrac{\sum x_i}{n} , n \to \infty \implies E(x) \to \mu_x
$$

- This is how casinos’ make money for blackjack, as they have a higher expected value compared to the player
- But does not apply for Poker, as the casino makes money from round fees, since Poker is played against players, not the casino

$$
P(\vert X-\mu \vert \ge \epsilon) \le \dfrac{\sigma^2}{n \epsilon^2}
$$

## Averaging Distributions

Given $n$ identically-distributed RVs with variance $\sigma^2$ and correlation $\rho$, the
variance of the mean is
$$
{\sigma^2}' = \rho \sigma^2 + (1 - \rho)\dfrac{\sigma^2}{n}
$$
## Other

- Zero-inflated/Hurdle distribution
- Multi-modal distributions
- Mixture of distributions