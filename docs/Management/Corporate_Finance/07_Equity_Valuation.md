# Equity Valuation

Common Shares

Returns

- Dividends
- Capital Gains

Difficult to estimate pricing, as there are so many variables in play

1. Unsure cashflows
2. Life of investment is infinite
3. No way to calculate required rate of return

It is frowned upon for a corporation to reduce dividends. Hence, if it increases dividends, it does so very carefully.

## Book Value Method

Most appropriate for established companies
$$
\begin{aligned}
\text{Value}
&= \dfrac{\text{Net Worth}}{\text{No of Shares}} \\
\text{Net Worth} &= \text{Assets} - \text{Liabilities}
\end{aligned}
$$
Assumption: book values are representative of true worth of company

## Dividend Capitalization Model

Value of equity is the sum of discounted dividends
$$
\begin{aligned}
P_t
&= \sum_{t=1}^\infty \dfrac{D_t}{(1+k)^t}
\end{aligned}
$$

### Constant Growth/Gordon Model

$$
\begin{aligned}
D_t &= D_{t-p} \times (1+g)^p \\
\implies P_t &= \frac{D_{t+1}}{k_\text{CS} - g} \quad \cancel{+ \frac{P_\infty}{(1+r)^\infty}}
\\
g
&= \text{ROE} \times \text{Retention Rate}
\end{aligned}
$$

where

- $g =$ dividend growth rate
  - non-zero constant percentage change of dividend from one year to next. If non-constant, we take average $g$ over a few years
  - Retention rate = Plowback rate
    - $= 1-\text{Payout Rate}$
- $k=$ market discount rate

| Dividend Growth |   $g$   |            |
| --------------- | :-----: | ---------- |
| No              |   $0$   | Perpetuity |
| Constant        | $\ge 0$ |            |

### Stochastic Growth and Discount Rate

$$
\begin{aligned}
E[\text{PV}]
&= E \left[ \sum_{t=0} c_t \cdot \left({\frac{1+g}{1+r}}\right)^t \right] \\
&= \sum_{t=0} c_t \cdot \left({\frac{1+E[g]}{1+E[r]}}\right)^t
\end{aligned}
$$

### PVGO

Present Value of Growth Opportunities

Represents value in an equity from expected growth opportunities

$$
\begin{alignedat}{1}
E[&\text{PVGO}]
&&= E[\text{Growth}] \\
&&&= P_{\text{Growth}}
&- P_{\text{No Growth}} \\
&\text{PVGO}_\text{Actual}
&&= P_\text{Actual}
&- P_{\text{No Growth}}
\end{alignedat}
$$

## Earnings

Earnings Multiplier
$$
P_t = (\text{P/E})_\text{Industry} \times \text{EPS}_\text{Firm}
$$

==**Shouldn’t $(\text{P/E})_\text{Industry}$ exclude the company we are analyzing?**==

## Free Cash Flow Model

Principle: Free cash flows will be

- Distributed as dividend
- Reinvested leading to capital appreciation

$$
\begin{aligned}
P_t
= \ & \dfrac{\text{FCFE}}{k}
\\
\text{FCFE}
= \ & \text{Surplus of time period} \\
= \
& \text{Net Income} + \text{Non-Cash Exp} \\
+ \
& \text{Investments in Working Capital} \\
+ \
&\text{Net Investment} + \text{Net Borrowing}
\end{aligned}
$$

Use the signs appropriately based on inflow/outflow

|                               |         |
| ----------------------------- | ------- |
| Net Income                    | Inflow  |
| Depreciation and Amortization | Inflow  |
| Investment in WC              | Outflow |
| Net Investment                | Outflow |
| Net Borrowing                 | Outflow |
