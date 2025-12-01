# Density Estimation

## Histogram DE

Limitations

- No of grid cells inc exponentially with dimensionality $k$
- Histogram is not ‘smooth’
- Shape of histogram depends on bin positions

## KDE

Kernel Density Estimation

Hyperparameter: Bandwidth $w$

![image-20240711232203056](./assets/image-20240711232203056.png){ loading=lazy }

Histogram has $b$ bins of width $w$ at fixed positions

KDE effectively places a bin of width $w$ at each $x \in \mathcal X$

To obtain $P(x)$, we count the % of points that fall in the bin centered at $x$

## Tophat KDE

$$
\begin{aligned}
P_w(x)
&= \dfrac{n(x ; w)}{n} \\
n(x ; w)
&= \Bigg\vert \Big\{ x_i: \vert \vert x_i - x \vert \vert \le w/2 \Big\} \Bigg\vert
\end{aligned}
$$

$n(x; w)=$ no points that are within a bin of width $w$ centered at $x$

To make it smooth, replace histogram counts with weighted averages
$$
P(x) \propto
\sum_{i=1}^n K(x, x_i)
$$
Interpretation

- We count the no of points ‘near’ $x$, but each $x_i$ has a weight $K(x, x_i)$ that depends on the similarity between $x$ and $x_i$
- We place a ‘micro-density’ $K(x, x_i)$ at each $x_i$; the final density $P(x)$ is their sum

### Common Kernels

- Linear
- Gaussian
- Tophat
- Epanechnikov
- Exponential
- Cosine

![image-20240711231651850](./assets/image-20240711231651850.png){ loading=lazy }

### Advantages

- Can approximate any data distribution arbitrarily well

### Disadvantages

- No of datapoints required for a good fit increases exponentially with dimensionality
- High space complexity: Need to store entire dataset to make queries
