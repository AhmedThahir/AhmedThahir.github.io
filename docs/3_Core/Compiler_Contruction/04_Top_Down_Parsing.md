## Top-Down Parsing

|               |  Recursive Descent  | Predictive Parsing |
| ------------- | :-----------------: | :----------------: |
| Method        |    Backtracking     |    Table-Driven    |
| Widely-used?  |          ❌          |         ✅          |
| Efficient?    |          ❌          |         ✅          |
| Grammar       |       General       |   LL(1) Grammar    |
| Tries to find | Leftmost derivation |                    |

- Recursive predictive parsing is a type of Recursive Descent, without backtracking
- Non-recusive predictive parser is also known as LL(1) parser

### Recursive Descent Parsing

![image-20230525172216371](./assets/image-20230525172216371.png){ loading=lazy }

## Computations

I would recommend just drawing the tree, and finding the corresponding set of terminals.

### First(X)

The first symbol accessible;

| X =          | $\exist$ X $\to$ |                                           | First(X) $\supset$ |
| ------------ | ---------------- | ----------------------------------------- | ------------------ |
| $\epsilon$   |                  |                                           | $\{\epsilon\}$     |
| Terminal     |                  |                                           | $\{X\}$            |
| Non-Terminal | $\epsilon$       |                                           | $\{\epsilon\}$     |
| Non-Terminal | $Y_1 Y_2 Y_n$    | $Y_i \to a; Y_{1, \dots, i-1} = \epsilon$ | $\{a\}$            |
| Non-Terminal | $Y_1 Y_2 Y_n$    | $Y_i \to \epsilon,  \forall i$            | $\{\epsilon\}$     |

![image-20230525173248539](./assets/image-20230525173248539.png){ loading=lazy }

### Follow(Non-Terminal)

| Non-Terminal                                                 |                                               |
| ------------------------------------------------------------ | --------------------------------------------- |
| Start Symbol $S$                                             | Follow(S) $\supset \{\$\}$                    |
| $A \to \alpha B \beta$                                       | Follow(B) $=$ First($\beta$) - $\{\epsilon\}$ |
| $A \to \alpha B$<br />$A \to \alpha B \beta$, and $\epsilon \in$ First($\beta$) | Follow(B) $\supset$ Follow(A)                 |

![image-20230525175015445](./assets/image-20230525175015445.png){ loading=lazy }

## LL(1) Grammar

No multiple-defined entries in parsing table

- Left-right scanning
- Leftmost derivation
- Lookahead of one i/p symbol at each step

## LL(1) Parser

### Components

|               |                                                              |
| ------------- | ------------------------------------------------------------ |
| Input Buffer  | String to be parsed, ending with `$`                         |
| Output        | Producting rule representing step of leftmost derivation sequence of string in input buffer |
| Stack         | Contains grammar symbols<br />`$` signifies bottom of stack<br />Initially, stack contains `$S`<br />Only `$` in stack signfies parsing complete |
| Parsing Table | 2 dimensional array $M[A, a]$<br />Each row is a non-terminal symbol<br />Each column is `$`/terminal symbol<br />Each entry holds production rule |

### Construction of Parser

|                                                              |                                                              | Replace with                                                 |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Elimination of left recursion**                            | $A \to A \alpha | \beta$                                     | $A \to \beta A'$<br />$A' \to \alpha A' | \epsilon$          |
| **Elimination of left factoring**                            | $A \to \alpha \beta_1 | \alpha \beta_2 | \gamma_1 | \gamma_2$ | $A \to \alpha A' | \gamma_1|\gamma_2$<br />$A' \to \beta_1 | \beta_2$ |
| **Calculate FIRST & FOLLOW**                                 |                                                              |                                                              |
| **Construction of parsing table**<br />For each $A \to \alpha$ |                                                              |                                                              |
| For each terminal a in FIRST($\alpha$)                       |                                                              |                                                              |
|                                                              |                                                              |                                                              |
| **Check if input string is accepted/not**                    |                                                              |                                                              |

This part is Incomplete