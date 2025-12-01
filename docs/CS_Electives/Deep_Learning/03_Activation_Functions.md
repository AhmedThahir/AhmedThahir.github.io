# Activation Functions

|                      Name                       |                                                Activation<br>$f(x)$                                                | Inverse Activation<br>$f^{-1} (y)$                                    | Output Type         |        Range        | Free from<br>Vanishing Gradients | Zero-Centered | Comment                                                                                                                                                             |
| :---------------------------------------------: | :----------------------------------------------------------------------------------------------------------------: | --------------------------------------------------------------------- | ------------------- | :-----------------: | :------------------------------: | :-----------: | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|                    Identity                     |                                                        $x$                                                         | $y$                                                                   | Continuous          |      $[-1, 1]$      |                                  |       ✅       |                                                                                                                                                                     |
|                Binary<br />Step                 |                               $\begin{cases} 0, &x < 0 \\ 1, & x \ge 0 \end{cases}$                                |                                                                       | Binary              |      ${0, 1}$       |                                  |       ❌       |                                                                                                                                                                     |
|                Tariff/<br />Tanh                |                                                     $\tanh(x)$                                                     | $\tanh^{-1}(y)$                                                       | Discrete            |      $[-1, 1]$      |                ❌                 |       ✅       |                                                                                                                                                                     |
|               Fast Softsign Tahn                |                                           $\dfrac{x}{1 + \vert x \vert}$                                           |                                                                       |                     |                     |                                  |               |                                                                                                                                                                     |
|                     ArcTan                      |                                                  $\tan^{-1} (x)$                                                   | $\tan(y)$                                                             | Continuous          |  $(-\pi/2, \pi/2)$  |                                  |               |                                                                                                                                                                     |
|                   Exponential                   |                                                       $e^x$                                                        | $\ln(y)$                                                              | Continuous          |    $[0, \infty]$    |                                  |               |                                                                                                                                                                     |
|        ReLU (Rectified<br />Linear Unit)        |                               $\begin{cases} 0, &x < 0 \\ x, & x \ge 0 \end{cases}$                                |                                                                       | Continuous          |    $[0, \infty]$    |                ✅                 |       ❌       | ✅ Computationally-efficient<br>❌ Discontinuous at $x=0$<br>❌ Dead neurons due to poor initialization, high learning rate; initialize with slight +ve bias           |
|       SoftPlus<br />(smooth alt to ReLU)        |                       $\dfrac{1}{k} \ln \Bigg \vert 1 + \exp \{ {k (x-x_0)} \} \Bigg \vert$                        | $\ln(e^y-1)$<br><br>$k ?$                                             | Continuous          |    $[0, \infty]$    |                                  |       ❌       |                                                                                                                                                                     |
|           Parametric/<br />Leaky ReLU           |                            $\begin{cases} \alpha x, &x < 0 \\ x, & x \ge 0 \end{cases}$                            |                                                                       | Continuous          | $[-\infty, \infty]$ |                ✅                 |       ✅       | All positives of ReLU                                                                                                                                               |
|          Exponential<br />Linear Unit           |                         $\begin{cases} \alpha (e^x-1), &x < 0 \\ x,&  x \ge 0 \end{cases}$                         |                                                                       | Continuous          | $[-\infty, \infty]$ |                                  |       ✅       | ❌ $\exp$ is computationally-expensive; though not significant in large networks                                                                                     |
|                     Maxout                      |                                          $\max(w_1 x + b_1, w_2 x + b_2)$                                          |                                                                       |                     |                     |                ✅                 |       ✅       | Generalization of ReLU and Leaky ReLU<br>❌ double the no of parameters                                                                                              |
|              Generalized Logistic               | $a + (b-a) \dfrac{1}{1+e^{-k(x-x_0)}}$<br><br>$a=$ minimum<br>$b=$ maximum<br>$k=$ steepness<br>$x_0 =$ $x$ center | $\ln \left \vert \dfrac{x-a}{b-x} \right \vert$<br><br>what about $k$ | Continuous          |      $[a, b]$       |      Depends on $a$ and $b$      |       ❌       | ❌ $\exp$ is computationally-expensive; though not significant in large networks<br>✅ Easy to interpret<br>- "probabilistic"<br>- saturating "firing rate" of neuron |
| Sigmoid/<br />Standard Logistic/<br />Soft Step |                                               $\dfrac{1}{1+e^{-x}}$                                                | $\ln \left \vert \dfrac{x}{1-x} \right \vert$                         | Binary-Continuous   |      $[0, 1]$       |                                  |       ❌       | ❌ $\exp$ is computationally-expensive; though not significant in large networks<br>✅ Easy to interpret<br>- "probabilistic"<br>- saturating "firing rate" of neuron |
|              Fast Softsign Sigmoid              |                                 $0.5 \Bigg( 1+\dfrac{x}{1 + \vert x \vert} \Bigg)$                                 |                                                                       |                     |                     |                                  |               |                                                                                                                                                                     |
|                     Softmax                     |   $\dfrac{e^{x_i}}{\sum_{j=1}^k e^{x_j}}$<br />where $k=$ no of classes<br />such that $\dfrac{\sum p_i}{k} = 1$   |                                                                       | Discrete-Continuous |      $[0, 1]$       |                                  |       ❌       |                                                                                                                                                                     |
|            Softmax with Temperature             |                           $\dfrac{e^{x_i/{\small T}}}{\sum_{j=1}^k e^{x_j/{\small T}}}$                            |                                                                       | Discrete-Continuous |                     |                                  |       ❌       | Exposes more “dark knowledge”                                                                                                                                       |

![activation_functions.svg](./assets/activation_functions.svg){ loading=lazy }

### Softmax with temperature

![image-20240516164505175](./assets/image-20240516164505175.png){ loading=lazy }

## Why use activation function for hidden layers?

Else, it would just be regular linear regression/logistic regression, so no point of hidden layers

Not using activation function $\implies$ using identity activation function

The only place identity activation function is acceptable is for the final output activation function in regression.

### Linear Regression

```mermaid
flowchart LR
a((x1)) & b((x2)) -->
d((h1)) & e((h2)) -->
y(("&ycirc;"))
```

$$
\begin{aligned}
\hat y
&= w_{h_1 \hat y} h_1 + w_{h_2 \hat y} h_2 \\
&= w_{h_1 \hat y} (w_{x_1 h_1} x_1 + w_{x_2 h_1} x_2) + w_{h_2 \hat y} (w_{x_1 h_2} x_1 + w_{x_2 h_2} x_2) \\
&= \cdots \\
&= w_1 x_1 + w_2 x_2
\end{aligned}
$$


### Logistic Regression

```mermaid
flowchart LR
a((x1)) & b((x2)) -->
d((h1)) & e((h2)) -->
s(("&sigma;")) -->
y(("&ycirc;"))
```

$$
\begin{aligned}
\hat y
&= \sigma(w_{h_1 \hat y} h_1 + w_{h_2 \hat y} h_2) \\
&= \sigma(w_{h_1 \hat y} (w_{x_1 h_1} x_1 + w_{x_2 h_1} x_2) + w_{h_2 \hat y} (w_{x_1 h_2} x_1 + w_{x_2 h_2} x_2)) \\
&= \cdots \\
&= \sigma(w_1 x_1 + w_2 x_2)
\end{aligned}
$$
## Why is non-zero-centering bad?
Since Non-zero-centered activation function such as sigmoid always outputs +ve values, it constrains gradients of all parameters to be
- all +ve
  or
- all -ve

This leads to sub-optimal steps (zig-zag) in the update procedure, leading to slower convergence