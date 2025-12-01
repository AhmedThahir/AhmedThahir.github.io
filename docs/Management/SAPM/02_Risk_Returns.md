# Risk and Returns

Note: Horizon need not always be $h=1$

## Return

“Return is backward-looking”
$$
r(t, h) = y_t - y_{t-h}
$$

## ROI

% change in series

Return on investment is in percentage relative to original investment

| ROI                         | $R_t$                                                        | Time Additive? | Multi-Period Return is __ sum of individual returns |
| --------------------------- | ------------------------------------------------------------ | -------------- | --------------------------------------------------- |
| Simple                      | $\dfrac{y_t - y_h}{y_h}$                                     | ❌              | Geometric                                           |
| Continuous<br />(Preferred) | $\ln \left \vert \dfrac{y_t}{y_h} \right \vert = \ln \vert y_t \vert - \ln \vert y_{t_h} \vert$ | ✅              | Arithmetic                                          |

$$
\text{CR} = \ln \vert 1 + \text{SR} \vert
$$

## Re-Investment Benefit

$$
\text{Re-Investment Benefit} = \text{IRR} - \text{ROI}
$$

Benefit that could be obtained by investing all intermediate inflows at the same ROI

## Yield

“Yield is forward-looking”
$$
Y_t = \dfrac{y_t - y_h}{y_t}
$$

## Dividends

Dividend rate are relative to face value, not your investment

### Dates

|                           |      |
| ------------------------- | ---- |
| Dividend Declaration Date |      |
| Ex-Dividend Date          |      |
| Record Date               |      |
| Payment Date              |      |

## Return Series

Assumed to be a random walk

## Expected Returns

$$
E(R) = \sum_i r_i \cdot P(r_i)
$$

## Risk

Chance of actual
return
differing from expected
return

Statistically quantified through variance/standard deviation of returns’ PDF

## Types of Unknowns

|                            | Systematic risk                    | Unsystematic risk                          | Uncertainty     |
| -------------------------- | ---------------------------------- | ------------------------------------------ | --------------- |
| Meaning                    | Sensitivity to market fluctuations | Personal factors                           | Unknown effects |
| Type                       | External<br />Macro                | Internal<br />Micro                        | External        |
| Minimizable                | ❌                                  | ✅<br />through diversification (portfolio) | ❌               |
| Risk Compensation expected | ✅                                  | ✅                                          | ❌               |

$$
\begin{aligned}
\text{Risk: } \sigma^2
&= \text{SR} + \text{UR} \\
\text{SR}
&= \beta^2 \cdot \sigma^2 (R_m)
\end{aligned}
$$

## Risk Measures

|                                |                                                              |
| ------------------------------ | ------------------------------------------------------------ |
| Standard Deviation             | $\sigma (R_p)$                                               |
| Beta<br />(Market sensitivity) | $\dfrac{\text{cov} (R_p, R_m)}{\sigma^2_{m}}$                |
| Semi Deviation                 | $\sigma (\text{Loss}_p)$<br />$\text{Loss}_t = \arg \max(R_t, 0)$ |

where $p=$ portfolio and $m=$ market

## Risk-Return Tradeoff

- Investors are rational and risk-averse: prefer less risk investments
- Investors expect risk premium: Investors are ready to take risk only with the expectation of higher return

![securities_risk_premium](assets/securities_risk_premium.svg){ loading=lazy }

$$
R_\min = R_f + \underbrace{\left (
\dfrac{R_m - R_f}{\sigma_m}
\right )}_\text{Market Price of Risk} \sigma
$$

## Jensen’s Inequality

Using Jensen’s Inequality
$$
E[f(x)] \ne f(E[x])  \\
\implies E[u(R)] > u(E[R])
$$
where

- $R$ is the return obtained
- $u(R)$ is the utility obtained from the return

## Effect of Frequency on Volatility

$$
V \propto \nu
$$

## Trading Days

|                 | Trading Days |
| --------------- | ------------ |
| Fixed-Income    | 365.25       |
| Variable-Income | 252          |

## Annualization

$$
\begin{aligned}
\text{Annual } E(R) &= 252 \times E(R) \\
\text{Annual } \sigma(R) &= \sqrt{252} \times \sigma(R)
\end{aligned}
$$

There are 252 trading days in a year

## IDK

Fixed-income securities are also very volatile

## YTM

Yield to Maturity = IRR of security if held until maturity

