# Sampling

Used when it is not feasible to analyze the entire population

Estimation: Using the sample to estimate population parameter(s)

## Population v Sample

| Property           |                          Population                          |                            Sample                            |
| ------------------ | :----------------------------------------------------------: | :----------------------------------------------------------: |
| Definition         | comprises of all units pertaining to a particular characteristic under study | is a part of a population, which is selected such that it is representative of the entire population |
| Size               |                             $N$                              |                             $n$                              |
| Mean               |                            $\mu$                             |             $\bar x = \dfrac {\sum_i^n x_i}{n}$              |
| Variance           |                          $\sigma^2$                          | $s^2 = \dfrac {\sum_i^n (x_i-\bar x)^2}{n \textcolor{hotpink}{-1}}$ |
| Standard Deviation |                           $\sigma$                           |                             $s$                              |

### Relations

$$
\begin{aligned}
\mathbb E(\bar x) &= \mu \\
\mathbb E[s^2_x] &= \sigma^2_x \\
\\
s^2_{\bar x} &= \frac{\sigma^2_x}{n} , s_{\bar x} = \frac{\sigma_x}{\sqrt n} \\
z_\text{sample} &= \frac{\bar x - \mu_x}{\sigma_x/\sqrt n }
\end{aligned}
$$

## Bessel’s Correction

$$
\begin{aligned}
\text{Var}(x) &= E[(x)^2] - (E[x])^2 \\
\implies
E[(x)^2] &= \sigma^2 + \mu^2 \\
\\
\text{Var}(\bar x) &= E[(\bar x)^2] - (E[\bar x])^2 \\
\implies
E[(\bar x)^2] &= \dfrac{\sigma^2}{n} + \mu^2 \\
\\
\implies \sigma^2
&= s^2_\text{uncorrected} + \text{Bias} \\
&= s^2_\text{uncorrected} + \dfrac{\sigma^2}{n} \\

\implies \sigma^2
&= s^2_\text{uncorrected} \times \dfrac{n}{\text{DOF}} \\
&= s^2_\text{uncorrected} \times \underbrace{\dfrac{n}{n-1} }_{\mathclap{\text{Bessel's Correction}}}
\end{aligned}
$$

Reasoning

- Degrees of freedom: We lose a degree of freedom when estimating $\bar x$
- Bias correction: While sampling with small sample size, less probable elements don’t show up which gives us an underestimated sample dispersion

## Sample vs Population Standard Deviation

### For Different Distributions

![image-20240128195458648](./assets/image-20240128195458648.png){ loading=lazy }

Higher the skew of population distribution, larger the sample size required to approximate the sample size to the population

### For the different population size

![image-20240128195800706](./assets/image-20240128195800706.png){ loading=lazy }

Sample vs Population SD does not depend on population size

## Interval Estimation

Confidence % $= 1- \alpha$

Most common is $95\%$ confidence interval estimate

$$
\begin{aligned}
1 - \alpha &= 0.95 \\
\alpha &= 0.05 \\
\alpha/\small 2 &= 0.025
\end{aligned}
$$

### Population mean

| $\sigma^2$ | $n$   | statistic | $\mu$ |
| :-------------------------------: | :---: | :-------------------------------: | :------: |
| known | any   | $z = \dfrac {\bar x - \mu}{\sigma / \sqrt n}$ | $\bar x \pm z_{\alpha/\small 2} \cdot \dfrac \sigma {\sqrt n}$ |
| unknown | $>30$ | $z = \dfrac {\bar x - \mu}{s/ \sqrt n}$ | $\bar x \pm z_{\alpha/\small 2} \cdot \dfrac s {\sqrt n}$ |
| unknown | $\le 30$ | $t = \dfrac {\bar x - \mu}{s / \sqrt n}$ | $\bar x \pm t_{\small n-1, \alpha/\small 2} \cdot \dfrac s {\sqrt n} \\(n-1) \to \text{deg of freedom}$ |


$$
\begin{aligned}
n &= \left( \frac{z_{\alpha/\small 2} \cdot \sigma}{w} \right)^2 \\
&= \left( \frac{z_{\alpha/\small 2} \cdot s}{w} \right)^2
\end{aligned}
$$

where

- $n$ is sample size
- $w$ is distance from $\mu$ = $\frac{\text{interval width}}{2}$

### Proportion

$$
\begin{aligned}
p &= \hat p \pm z_{\alpha/\small2} \sqrt {\frac{\hat p \hat q}{n}} \\
\hat p &= \frac x n = \frac{\text{Favorable no of cases}}{\text{Total no of cases}} \\
\hat q &= 1 - \hat p
\end{aligned}
$$

### Population Variance / SD

$$
\begin{aligned}
\sigma^2 &= \left[
\frac{(n-1)s^2}{\chi^2_{(n-1), (\alpha/\small 2)}},
\frac{(n-1)s^2}{\chi^2_{(n-1), (1-\alpha/\small 2)}}
\right] \\
\sigma &= \sqrt {\sigma^2}
\end{aligned}
$$

## Inequalities

Let $x$ be a random variable such that $x_i \in [a, b]$

Consider

- sample size $n$
- $\epsilon > 0$

### Hoeffding’s Inequality

$$
\begin{aligned}
P (\vert \hat \mu − \mu \vert > \epsilon)
& \le 2 \exp \left[ \dfrac{-2 n \epsilon^2}{(b-a)^2} \right]
\\
\sum_{b}^B P (\vert \hat \mu_b − \mu_b \vert > \epsilon)
& \le 2 \exp \left[ \dfrac{-2 n \epsilon^2}{(b-a)^2} \right] \times B
\end{aligned}
$$

where

- $\mu$ is any parameter and $\hat \mu$ is its estimate
- $n>0$
- $\epsilon > 0$
- $B=$ no of ‘bins’

Notes

- We want low $P (\vert \hat \mu − \mu \vert > \epsilon)$
- Even though $P (\vert \hat \mu − \mu \vert > \epsilon)$ will depend on $\mu$, the bound is independent of $\mu$

### Vapnik-Chervonenkis Inequality

$$
P (\vert \bar x − \mu \vert > \epsilon) \le 4 \cdot m_h(2n) \cdot \exp \left[ \dfrac{-1}{8} n \epsilon^2 \right]
$$

Where $m_h(n) = 2^n$
