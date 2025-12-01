# Types of Experiments

| Type                                 |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | Free from Self-Selection | External Validity | Example                                                                                                                                                                                                                                                                                                     |                                                                | LATE                    |
| ------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------ | ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------- | ----------------------- |
| RCT<br />(Randomized Control Trials) |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | ✅                        | ⚠️                |                                                                                                                                                                                                                                                                                                             |                                                                | Only for compliers      |
| Natural/<br>Quasi                    | A situation where the researcher does not assign treatment to individuals<br><br>Treatment is “as if” random, as implicit randomization occurs                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | ❌                        | ✅                 |                                                                                                                                                                                                                                                                                                             |                                                                |                         |
| Regression Discontinuity Design      | Discrete treatment status determined by an underlying continuous variable, which is used for quasi experiments<br><br>Assumption: People right before and after threshold are identical<br><br>Running/forcing variable: Index/measure that determines eligibility<br><br>Cutoff/cutpoint: threshold that formally assigns access to program<br><br>Limitations<br>- Requires lots of data in the neighborhood of the threshold<br>- Poor generalizability: The validity of the results is usually restricted to this region<br>- Throws away the lot of information in the non-random parts<br>- Doesn’t allow building structural causal model |                          |                   | Uni admission cutoff provides a natural experiment on uni education. Students **just** above/below are likely to be very similar. For these students, uni education is “as if” random. Comparing these students (ones that went to uni/not) produces an estimate of the causal effect of college education. | ![image-20240213172957152](assets/image-20240213172957152.png){ loading=lazy } | People in the bandwidth |
| Differences-in-Differences           | 2 time-series process $y_1$ and $y_2$ have the factors affecting them                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |                          |                   |                                                                                                                                                                                                                                                                                                             | ![image-20240213175121148](assets/image-20240213175121148.png){ loading=lazy } |                         |
| Instrumental Variables               | IV technique helps work around simultaneous causal relationships<br>- Education -> Earnings -> Education -> ...<br>- Supply --> Demand --> Supply --> ...                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |                          |                   |                                                                                                                                                                                                                                                                                                             |                                                                | Only for compliers      |

## Compliance


| Type          | What they do when assigned to control group $T=0$ | What they do when assigned to treatment group $T=1$ |
| ------------- | ------------------------------------------------- | --------------------------------------------------- |
| Compliers     | $T=0$                                             | $T=1$                                               |
| Always takers | $T=1$                                             | $T=1$                                               |
| Never takers  | $T=1$                                             | $T=1$                                               |
| Defiers       | $T=0$                                             | $T=1$                                               |

## Differences-in-Differences

Let

- Control: $y_0$ be the time series with $x=0$
- Treated: $y_1$ be the time series with $x=1$
- $D_t$ be the difference of the 2 series

$$
\begin{aligned}
y_{0t}
&= f(t) + \beta_1 (T=0) \\
&= f(t) \\
y_{1t} &= f(t) + \beta_1 (T=1) \\
D_t &= (y_1 - y_0)_t
\end{aligned}
$$

### Assumptions

- Parallel trends: $f_1(t) = f_0(t)$
	- confirmed by evaluating regions without the treatment
- No differential timing: Check Goodman-Bacon decomposition
- Absence treatment: no other variables
- Difference between the treatment & the control group is time-invariant
	- any difference in their difference must be due to the treatment effect.

### Why not other way?
- Wrong ways: Impossible to know if change happened because of treatment or naturally
	- Only comparing treatment group before/after
	- Only comparing treatment/control group at a particular time

## RDD

### Threats

#### Manipulation

People may change behavior when they know of the cutoff

Discontinuity exists in the running variable even without any treatment

![](assets/rdd_manipulation.png){ loading=lazy }
![](assets/rdd_manipulation_nba.png){ loading=lazy }

Check with McCrary Density Plot

![](assets/McCrary_Density_Test.png){ loading=lazy }



#### Non-Compliance

People on the margin of the cutoff may/may not get treatment, by misrepresenting the running variable
- Some people may not want treatment even though they crossed the cutoff
- Others may request access to the above discarded treatment spots

This is different from manipulation, where the actual running variable comes out different

For eg: Misreporting income

![](assets/imperfect_compliance.png){ loading=lazy }
### Types

|       | $T$                                                         |                                                                                                                                        |                           |
| ----- | ----------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ------------------------- |
| Sharp | $\begin{cases} 1, & z \ge z_0\\ 0, & \text{o.w}\end{cases}$ |                                                                                                                                        |                           |
| Fuzzy | $\begin{cases} , & z \ge z_0\\ , & \text{o.w}\end{cases}$   | Doubly-local effect: CACE only around cutoff<br><br>Useful when there is non-compliance<br><br>Use above/below threshold as instrument | ![](assets/fuzzy_rdd.png){ loading=lazy } |

## What to Choose

![World Bank Impact Evaluation in Practice, p. 191](assets/doe_what_to_choose_when.png){ loading=lazy }