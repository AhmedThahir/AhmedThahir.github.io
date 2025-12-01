# Decision Trees

Piecewise constant model that adaptively learns to divide predictor space into different regions, and then fits a model in each region, without human intervention for deciding the cuts

Can be applied for regression and classification

## Advantages

- Interpretable, for small trees
- Little data processing required

## Disadvantages

- Small trees are not powerful
- Large trees tend to overfit, and are hard to regularize
- Do not work well for modelling linear relationships
- Cannot extrapolate well
- Decision regions tend to be highly-fragmented
- Resulting function (also Decision boundary for classification) is very non-smooth and blocky

![image-20240709120643099](./assets/image-20240709120643099.png){ loading=lazy }

## Terms

| Term                | Meaning                                                      |
| ------------------- | ------------------------------------------------------------ |
| Internal Nodes      | Labelled by attributes names, to be tested on an attribute<br />Contain the splitting attributes |
| Leaf/Terminal Nodes | Labelled by class labels                                     |
| Edges               | determined by the nuo of outcomes on n attribute test conditions |
| Size                | Number of leaves                                             |

## Goal

Find cuts to obtain $R_m$ and improve in-sample fit, while minimizing out-of-sample loss

Since it is computationally-infeasible to consider every
possible partition of the predictor space, we adopt a forward stepwise procedure

## Decision Tree Approaches

| Approach      | Steps                                                        | Disadvantage                 |
| ------------- | ------------------------------------------------------------ | ---------------------------- |
| **Greedy**    | Divide the problem into different steps, take decisions at each step<br /><br />Build tree in Top-Down manner<br />Split train set into purer subsets (the best split) @ every node | Backtracking is not possible |
| **Recursive** |                                                              |                              |

## Regression Tree

$$
\begin{aligned}
\hat y &= \sum_{m=1}^M \hat y_m \cdot I \{ x \in R_m \} \\
\hat y_m &= \underset{x_i \in R_m}{\text{mean}} \ (y_i)
\end{aligned}
$$

where

- $M =$ no of leaves = no of regions
- $R_m =$ region $m$
- $n_m=$ no of obs in $R_m$

For every observation that falls into the region Rm, we make the same prediction, which is simply the mean of the response values for the training observations in $R_m$

### CART Algorithm

Greedy recursive partitioning algorithm that divides predictor space through successive binary splits, until a stopping criterion is reached.

This is greedy because at each step of the tree-building process, the optimal split is made at that particular step, rather than looking ahead and picking a split that will lead to a better tree in some future step

#### Steps

1. At each step, generate binary split to divide predictor space into two regions that achieves the biggest reduction in $E_\text{in}$

   - The 2 regions are as homogenous in response $y$ as possible

     - $R_\text{left} = \{ x \in R: x_j < s \}$
     - $R_\text{right} = \{ x \in R: x_j \ge s \}$

   - For regression this means choosing $j, s$ such that

     $\arg \min\limits_{j, s} \left \{ \sum \limits_{x_j \in R_\text{left}} L(y_i, \hat y_\text{left}) + \sum \limits_{x_j \in R_\text{right}} L(y_i, \hat y_\text{right}) \right \}$

2. Prune the tree by choosing a subtree $T \subset T_0$ that minimizes

   $\sum \limits_{m=1}^{\vert T \vert} \sum \limits_{x_j \in R_m} L(y_i, \hat y_m) + \alpha \vert T \vert, \quad \forall \alpha \ge 0$

   - $\vert T \vert =$ size of tree
   - $\alpha$ controls tradeoff b/w subtree’s complexity and fit to training data
     - $\alpha=0 \implies T=T_0$
     - $\vert T \vert \propto \dfrac{1}{\alpha}$
     - Optimal $\alpha$ obtained through cross-validation

## Classification Tree

$$
\begin{aligned}
\hat y
&= \sum_{m=1}^M \hat c_m \cdot I (x \in R_m) \\
\hat c_m &= \underset{x_i \in R_m}{\text{mode}} \ (y_i)
\end{aligned}
$$

$\hat c_m =$ most frequent class in $R_m$ 

### Node Impurity

|                        |                                                      |
| ---------------------- | ---------------------------------------------------- |
| Misclassification Rate | $1 - {\hat p}_m$                                     |
| Gini-Index             | $\sum \limits_c^C \hat p_c^m (1 - \hat p_c^m)$       |
| Cross-Entropy          | $- \sum \limits_c^C \hat p_c^m \cdot \ln \hat p_c^m$ |

### Steps

1. Pick an independent variable

2. Find Entropy of all classes of that independent variable

$$
H(C_i) =
-P_\text{Pos} \log_2 (P_\text{Pos})
-P_\text{Neg} \log_2 (P_\text{Neg})
$$

3. Calculate gain of each independent variable for current set/subset of data

$$
\begin{aligned}
&\text{Gain}\Big(
\text{Value}(C_1), C_2
\Big) \\
=& H \Big(\text{Value}(C_1) \Big) \\
   & - \left[
\sum_{i=1}
\frac{n (C_2=\text{Value}_i)}{n \Big(\text{Value}(C_1) \Big)}
\times
H(C_2=\text{Value}_i)
\right]
\end{aligned}
$$

4. Pick the independent variable with the highest gain

5. Recursively repeat for each independent variable

### Hunt’s Algorithm

Let

- $t$ be a node
- $D_t$ be train dataset @ node $t$
- $y$ be set of class labels $\{ C_1, C_2, \dots, C_n \}$

1. Make node $t$ into a leaf node. Label $t$ with class label $y_t$
2. Split using appropriate attribute
   1. Apply splitting criteria for each attribute and obtain impurity of split using that attribute
   2. Pick the attribute that gives the lowest impurity
   3. Label the node $t$ with this attribute
   4. Determine the outcomes
   5. Create nodes for each outcome
   6. Draw the edges
   7. Split the dataset
3. Repeat the steps for each subtree now

### Additional Cases

#### Empty Subset

Let’s say when splitting your data, you end up having an empty subset of the data

1. Make node $t$ into a leaf node
1. $t$ is labelled as the majority class of the parent dataset

#### All records have identical attribute values

1. Make $t$ into a leaf node
2. $t$ is labelled as the majority class represented in the current subset

#### Number of records fall below minimum threshold value

- Make $t$ into a leaf node
- $t$ is labelled as the majority class represented in the current subset

### Splitting Continuous Attributes

#### Binary Split

Choose a splitting value that results in a purer partition

#### Multi-Way Split

- Apply discretization
- Each bin will be a different node

```mermaid
flowchart LR

subgraph Multi-Way Split
direction TB
s2((Salary))

a2(( ))
b2(( ))
c2(( ))
d2(( ))
e2(( ))

s2 -->|< 10k| a2
s2 -->|10k - 30k| b2
s2 -->|30k - 50k| c2
s2 -->|50k - 80k| d2
s2 -->| > 80k| e2
end

subgraph Binary
direction TB
s1((Salary))

end
```

### Challenges

- How to split training records?
  - Find best splitting attribute (Test on Attribute)
  - Measure for goodness of split
- Stopping conditions
  Stop at situations that result in fully-grown tree (the tree has learnt all the important characteristics, which may not be optimal; in that case we may require some other stopping conditions)
    - When all records at a node have the same attribute value
    - When all records at a node have the same class label value
    - When a node receives an empty subset

## Attribute Test Condition and Outcomes

### Nominal

Binary split will have $2^{k-1} - 1$ combinations of the binary split, where $k=$ no of values. For eg:

```mermaid
flowchart LR

subgraph 1
direction TB
aa[Attribute] --> v1a[v1] & notv1a["Not v1 <br> (v2 or v3 or v4)"]
end

subgraph 2
direction TB
ab[Attribute] --> v1b[v1 or v2] & notv1b["Not (v1 or v2) <br> (v3 or v4)"]
end
```

## Missed this class

[Lec20.pdf](../assets/Lec20.pdf) 

## Terms

Variables

$$
\Delta = I(Parent) - \sum_{j=1}^k
\underbrace{
\frac{N(V_j)}{N} I (V_j)
}_\text{Weighted Average Impurity}
$$

- $I(\text{Parent}) =$ Impurity at parent
- $N =$ no of records before split (parent)
- $k =$ no of splits
- $V_j =$ denote a split
- $N(V_j) =$ no of records at split $V_j$
- $I(V_k) =$ impurity at child(split) $V_j$

## Steps to choose splitting attribute

- Compute degree of impurity of parent node (before splitting)
- Compute degree of impurity of child nodes (after splitting)
- Compute weighted average impurity of split
- Compute gain $(\Delta)$
  Larger the gain, better the test condition
- Choose attribute with largest gain
- Repeat for all attributes

### Note

==Information gain means the impurity measure is entropy==