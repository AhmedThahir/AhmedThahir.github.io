## Risk Neutral Valuation

Suppose our economy includes stock $S$, riskless money market account $B$ with interest rate $r$ and derivative claim $f$

Assuming there’s only 2 possible outcomes at time $dt$

![image-20240313102130100](assets/image-20240313102130100.png){ loading=lazy }

### Naive Approach

Current price of a derivative claim is determined by current price of portfolio which exactly replicates the payoff of the derivative at maturity

Consider Forward contract with pays $S-K$ at time $dt$. One could think that its strike $K$ should be defined by the “real world” transition probability $p$
$$
p(S_1 - k) + (1-p) (S_2 - k) = p S_1 + (1-p) S_2 - k \\
p = 1/2 \implies k_0 = (S_1 + S_2)/2
$$


1. Borrow $S_0$ to buy stock. Enter forward contract with strike $k_0$
2. In time $dt$ deliver stock in exchange for $k_0$ and repay $S_0 e^{r \ dt}$

- If $k_0 > S_0 e^{r \ dt}$, riskless profit
- If $k_0 < S_0 e^{r \ dt}$, definite loss

Notes

- Given current price of the stock and assumptions on the dynamics of stock price, there is no uncertainty about the price of a derivative.
- Price is defined only by the price of the stock and not by the risk preferences of the market participants
- Mathematical apparatus allows us to compute current price of a derivative and its risks, given certain assumptions about the market

## General derivative claim

For a claim $f$, find $a$ and $b$ such that
$$
\begin{aligned}
f_1 &= a S_1 + b B_0 e^{r dt} \\
f_2 &= a S_2 + b B_0 e^{r dt} \\
\implies f_0 &= a S_0 + b B_0
\end{aligned}
$$

$$
\begin{aligned}
a &= \dfrac{f_1 - f_2}{S_1 - S_2} \\
b &= \dfrac{S_1 f_2 - S_2 f_1}{(S_1 - S_2) B_0 e^{r \ dt}}
\end{aligned}
$$

$$
f_0 = e^{- r \ dt} \Big( f_1 q + f_2 (1-q) \Big) \\
q = (S_0 e^{r \ dt} - S_2)/(S_1 - S_2), & q \in (0, 1) \\
\implies q S_1 + (1-q) S_2 = e^{r \ dt} S_0
$$

## Black-Scholes

Assumes that stock has log-normal dynamics
$$
dS = \mu S dt + \sigma S dw
$$
where $W$ is a Brownian motion: $dW$ is normally-distributed with mean 0 and standard deviation $\sqrt{dt}$ 

We want t find a replicating portfolio such that
$$
df = a dS + b dB
$$

$$
(dS)^2 = \sigma^2 S^2 dt
$$

$$
\dfrac{\partial f}{\partial t} + \dfrac{1}{2} \dfrac{\partial^2 f}{\partial S^2} \sigma^2 S^2 + \dfrac{\partial f}{\partial S} r S - rf = 0
$$

