# Regularized Pricing & Risk Models

https://www.youtube.com/watch?v=aga-Tak3c3M&list=PLUl4u3cNGP63ctJIEC1UnZ0btsphnnoHR

## Bond Duration

Sensitivity of bond price $(\ln P)$ to bond yield $y$

- Duration gives the “weighted time”
- Duration of zero coupon bond = maturity
- Duration of regular coupon bond < maturity
- As there is only a fixed $y$ for all payment dates, the duration is a sensitivity to “parallel” move

Good measure for price changes for small variation in yield

Second derivative required for large changes in yield

$$
\begin{aligned}
P &= \sum \limits_{i=1}^n e^{-y t_i} C_i \\
P_y &= \dfrac{\partial P}{\partial y}= - \sum \limits_{i=1}^n t_i e^{-y t_i} C_i \\
\implies d &= \dfrac{P_y}{P} \\
c &= \dfrac{\partial^2 P}{\partial y^2} = \sum \limits_{i=1}^n t_i^2 e^{-y t_i} C_i \\
\end{aligned}
$$
where

- $d=$ Bond Duration
- $c =$ Bond convexity: Always positive
- $P=$ price of bond
- $y=$ yield of bond
- $C_i = i$th cashflow

## Swaps

Valuing fixed and float legs of the swap

Swap can be hedged with bond
$$
\begin{aligned}
\text{PV}_\text{fixed} &= \sum_i C \delta_i \Alpha_i = C \sum_i w_i \\
\text{PV}_\text{float} &= \sum_i C r_i \delta_i \Alpha_i = \sum_i r_i w_i \\
\text{PV}_\text{fixed} &= \text{PV}_\text{float} \\
\implies C &= \dfrac{\sum_i r_i w_i}{\sum_i w_i}
\end{aligned}
$$
where

- $c =$ swap rate (fixed leg coupon)
  - Weighted sum of forward rates (assuming same frequency of payments of fixed & floating legs)
- $\Alpha_i =$ discount factor for payment date $i$
- $\delta_i =$ day count fraction
- $r_i =$ forward rate (floating rate of future payment)

## Yield Curve

1. Select input instruments
2. Choose interpolation
   - Interpolation space (daily forward rates, zero rates, etc)
   - Spline (piece-wise constant, linear, tension spline, etc)
   - Knot points and model parameters
3. Calibrate
   - Solve for spline parameters such that input instruments are re-priced at par

### Bond Spread

$$
P= \sum_{i=1}^n e^{-s t_i} \Alpha_i C_i
$$

where

- $\Alpha_i =$ discount factor for payment date $i$ computed from curve
- $s=$ bond spread
- $t_i =$ future time of payment in years
- $C_i = i$th cashflow

If the model is available for typical movements of the curve embedded in $\Alpha_i$ we can build more effective risk model for bond, rather than using single “parallel” shift mode (bond duration)

## Hedging

$$
x = \arg \min {\left \vert \left \vert F^T (r + Hx ) \right \vert \right \vert}^2
$$

where

- $r =$ portfolio risk
- $H =$ hedging portfolio risks
- $x =$ weights of hedging instruments
- $F =$ market scenarios (factors)

### PCA

Use SVD to decompose market movements data $D$ into principal comments $P$ and corresponding uncorrelated market dynamics $U$ with weights $S$
$$
D = U \cdot S \cdot P^T
$$
Use few SVD components with largest singular values - low rank approximation of market data
$$
P^T (r + Hx) = 0
$$

## PCA Risk Model

“Formally” tuned to historical data

Hedge coefficients are unstable, especially if historical window is short

Costly to re-hedge when PC factors change

Instability is coming from PCs corresponding to small singular values

Over-fitting to historical data

NO assumptions of shape of yield curve

## Regularized Risk Models

Assumption: Forward rates move smoothly
$$
H^T R = I \\
{\vert \vert L \cdot J \cdot R \vert \vert}^2 \to \min \\
R \sim \Big(HH^T + \lambda^2 (L \cdot J)^T \cdot L \cdot J \Big)^{-1}
$$
where

- $J =$ Jacobean matrix translating shifts of yield curve inputs to movements of forward rates
- $L=$ Smoothness regularity matrix
- $\lambda =$ regularization parameter

## Pricing Model

![image-20240225131906900](./assets/image-20240225131906900.png){ loading=lazy }

## HJM Heath-Jarrow-Morton Model

Evolution of forward rates
$$
{df}_{t, s} = \mu_{t, s} dt + f_{t, s}^\beta V(t, s) \rho (t, s) \cdot dB_t^Q
$$
where

- $f =$ forward rate
- $\mu=$ drift
- $\beta=$ model skew factor
- $\rho=$ Correlation/factor structure
- $V(t, s)=$ parametric volatility surface
- $d B_t^Q =$ Brownian motion

## Regularized Volatility Surface

![image-20240225132738320](./assets/image-20240225132738320.png){ loading=lazy }

### Challenges

- High dimensionality
  - Need to calibrate many elements
- Large memory requirement to store matrix
- Relatively small number of calibration instruments
- Under-determined problem
- Sensitivity areas of calibration instruments overlap significantly
- Ill-posed inverse problem
- Unstable, noisy solution
- Need regularity conrtaints
- Has to be smooth to produce realistic prices for similar instruments

### IDK

Represent volatility surface as linear combination of $n$ basis functions
$$
v = v_0 + \beta x
$$
where

- $v =$ vector of volatility grid elements
- $\beta=$ matrix corresponding to basis functions
- $x=$ vector of weights

Make $n$ equivalent to number of calibration instruments $M$

“Formally” unambiguous

Make basis functions piecewise constant matching sensitivity of calibration instruments, 0 otherwise

## Sensitivities

$$
\begin{aligned}
J_{ij}
&= \dfrac{\partial q_i}{\partial x_j} \\
q
&= J \cdot x \\
&= \ln \dfrac{q_{mdl}}{q_0} \\
q_\text{in} &= \ln \dfrac{q_\text{market}}{q_0}
\end{aligned}
$$

where

- $q_{mdl} =$  model price
- $q_\text{market} =$  market price
- $q_0 =$ base price
- $x=$ vector basis functions coefficients
