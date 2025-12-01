# Value Modelling



|                                                       | VAR                                           | VAG                               |
| ----------------------------------------------------- | --------------------------------------------- | --------------------------------- |
| Meaning                                               | Value at Risk                                 | Value at Gain                     |
| $p_x = x \%$ VAR/VAG is values for __ of distribution | Bottom $x \%$                                 | Top $x \%$<br />Bottom $(1-x) \%$ |
| Probability of __ given level                         | Losses <                                      | Gains >                           |
| Preferred for                                         | Lending (concerned about receiving repayment) | Investing (interested in gain)    |
| Example                                               | ![VaR_Graph](assets/VaR_Graph.png){ loading=lazy }          |                                   |

Note: Both are ==**one-sided tails**==

## Target Curve

Cumulative Distribution of outcomes (rarely frequency distribution)

Goes from VAR % to VAG %

![image-20240222014852101](assets/image-20240222014852101.png){ loading=lazy }

### Dominance

If target curve 1 always to right of another, it dominates

But it is not necessary that one alternative always performs better than other in all situations, as best case for one situation may be bad for another situation

## Evaluation Methods

| Method                         |                                                              |
| ------------------------------ | ------------------------------------------------------------ |
| Historical                     | Percentile of historical values                              |
| Parametric/Variance-Covariance | 1. Calculate covariance matrix of all securities<br />2. Annualize them<br />3. Calculate portfolio standard deviation: $\sigma_p = \sqrt{w' \Sigma w}$<br /> |
| Monte Carlo Simulation         | 1. Obtain dist statistics: Mean, Variance, …<br />2. Run simulation<br />3. Get the required percentiles |
