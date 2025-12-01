# Hyper-Parameter Tuning

Sometimes better to do in log-space, rather than linear space

|                |                                                                                                                    | Advantage | Disadvantage              |
| -------------- | ------------------------------------------------------------------------------------------------------------------ | --------- | ------------------------- |
| Manual         |                                                                                                                    |           | Time-Consuming            |
| Grid Search    |                                                                                                                    |           | Computationally-expensive |
| Random Search  |                                                                                                                    |           | Non-deterministic         |
| Evolutionary   | Randomization, Natural Selection, Mutation                                                                         |           |                           |
| Bayesian       | Probabilistic model of relationship b/w cost function and hyper-parameters, using information gathered from trials |           |                           |
| Gradient-Based | Treat hyper parameter tuning like parameter fitting                                                                |           |                           |
| Early-Stopping | Focus resources on settings that look promising<br />eg: Successive Halving                                        |           |                           |

## Speed Up

- Parallelizing
- Caching
- Random sampling: Won’t work with caching

![image-20240317160544276](./assets/image-20240317160544276.png){ loading=lazy }

## Clustering

### Elbow Method

Plot cost function as function of no of clusters

![image-20240711155441455](./assets/image-20240711155441455.png){ loading=lazy }

## Visualization


| Visualization        |                                                     | More than 3 hyperparameters | Simple |
| -------------------- | --------------------------------------------------- | --------------------------- | ------ |
| Contour              |                                                     | ❌                           | ✅      |
| Parallel Coordinates | ![](assets/Hyperparameter_Tuning_Visualization.png){ loading=lazy } | ✅                           | ❌      |
