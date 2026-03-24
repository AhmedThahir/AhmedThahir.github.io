# Time Value of Money

You should never compare money across different time instants. We can only compare at the same instant.

| When we take cashflow ___ in time | Name        |
| --------------------------------- | ----------- |
| forward                           | compounding |
| backward                          | discounting |

Return for every investment is a compensation

- Time
- Inflation
- Risk

In a finance interview, if you’re not sure of the answer, just say it’s compounding 😭😂

## Keywords

|                                                                                                           | Denotion   | Expressed as | Value of something at                                 |
| --------------------------------------------------------------------------------------------------------- | ---------- | ------------ | ----------------------------------------------------- |
| Present Value                                                                                             | PV         | Currency     | $t = 0$ (not even $t \approx 0$)                      |
| Future Value                                                                                              | FV         | Currency     | $t > 0$                                               |
| Interest Rate<br />Discount Rate<br />Compound Rate<br />Opportunity cost of capital<br />Required return | $r$        | %            | Exchange rate between present & future value          |
| Number of Periods                                                                                         | $n$ or $t$ |              |                                                       |
| Timeline                                                                                                  |            |              | Graphical reprsesentation of the timing of cash flows |

## Singular Cashflow Formula

$$
\begin{aligned}
\text{FV} &= \text{PV} \times \underbrace{(1+r)^t}_{\text{Compound Factor}} \\
\implies \text{PV} &= \text{FV} \times \underbrace{\frac{1}{(1+r)^t}}_\text{Discount Factor}
\end{aligned}
$$

## Multiple Cashflows

$$
\begin{aligned}
\text{FV} &= \sum_{t=0} c_t (1+r)^t \\
\text{PV} &= \sum_{t=0} \frac{c_t}{(1+r)^t}
\end{aligned}
$$

where $c_t$ can be

| Flow Type |         |
| --------- | ------- |
| Inflow    | $c_t>0$ |
| Outflow   | $c_t<0$ |

## Stochastic Discount Rate

$$
\begin{aligned}
E[\text{PV}]
&= E \left[ \sum_{t=0} \frac{c_t}{(1+r)^t} \right] \\
&\ge \sum_{t=0} \frac{c_t}{(1+E[r])^t}
\end{aligned}
$$

## Types of Interest

If $P =$ original principal amount

| Type                    | FV                        |
| ----------------------- | ------------------------- |
| Simple                  | $P \times (1+r) \times t$ |
| Compound<br />(Default) | $P \times (1+r)^t$        |

## Types of Cashflows

Infinite series of cashflows which has

eg: Preference share in a corporation

|                          | Perpetuity    | Annuity                                          |
| ------------------------ | ------------- | ------------------------------------------------ |
| Finiteness               | Infinite      | Finite                                           |
| Term                     | Forever       |                                                  |
| Fixed Cashflow           | ✅             | ✅                                                |
| Occurs every time period | ✅             | ✅                                                |
| Present Value            | $\frac{c}{r}$ | $\frac{c}{r} \left[ 1-\frac{1}{(1+r)^t} \right]$ |
| Future Value             | N/A           | $\frac{c}{r} \left[ (1+r)^t - 1 \right]$         |

### Conceptual understanding of long-term loan

Every equal installment is actually a combination of

- interest payment
- principal repayment

As time goes on, your installment will be constituting: less of interest repayment & more of principal repayment

## I missed a few classes

## Interest Rates

### APR

Annual Percentage Rate

### EAR

Effective Annual Rate

The actual interest rate you are paying

$$
\text{EAR } = \left(
1 + \frac{\text{APR}}{m}
\right)^m - 1
$$

where $m =$ interest compounding frequency

This is the value of $r$ we use when calculating present/future value

### Compounding Frequency

|             | $m$                |
| ----------- | ------------------ |
| Annual      | 1                  |
| Semi-Annual | 2                  |
| Quarterly   | 4                  |
| Monthly     | 12                 |
| Daily       | 365                |
| Hourly      | 365 * 24           |
| Minutely    | 365 * 24 * 60      |
| Second      | 365 * 24 * 60 * 60 |

As we go from annual compounding towards more frequent compounding frequency, we are moving from **discrete compounding** to **continuous compounding**

## Compounding Cycle

Frequency of compounding

Let $m$ be the compounding cycle, ie number of compounding per year
$$
\begin{aligned}
\text{FV} &= \text{PV} \cdot (1+r)^t \\
&= \text{PV} \left(1 + \dfrac{r}{m} \right)^{mt} & \text{(Discrete)} \\
&= \text{PV} \times e^{rt} & \text{(Continuous)}
\end{aligned}
$$

## IDK

If you are in the middle of time period, and certain cashflows have already been taken,
$$
\text{PV}' = \text{PV} \times \dfrac{1}{(1+r)^{t_a/t_b}}
$$

- $t_a=$ Time elapsed in current time period
- $t_b=$ Time Period
