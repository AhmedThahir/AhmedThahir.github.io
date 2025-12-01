- Encoder processes inputs
- Decoder generates outputs

![image-20230527225235767](./../assets/image-20230527225235767.png){ loading=lazy }

![image-20230529222625107](./../assets/image-20230529222625107.png){ loading=lazy }

## Seq2Seq

Used for language translation

![image-20230527225342777](./../assets/image-20230527225342777.png){ loading=lazy }

### Encoder

Reads input sequence

Standard RNN model without output layer

Encoder’s hidden state in last time step is used as the decoder’s initial hidden state

### Decoder

RNN that generates output

Fed with the targeted sentence during training

## Search Algorithms for Picking Weights

Let

- $n =$ output vocabulary size
- $T = L =$ max sequence length

| Search Algorithm |                                                              | Time Complexity                            |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------ |
| Greedy           | Used in seq2seq model during prediction<br />It could be suboptimal | $O(nT)$                                    |
| Exhaustive       | Compute probability for every possible sequence<br />Pick the best sequence | $O(n^T)$<br />❌ computationally infeasible |
| Beam             | We keep the best $k$ (beam size) candidates for each time<br />Examine $kn$ sequences by adding new item to a candidate, and then keep the top-$k$ ones<br />Final score of each candidate<br />$= \frac{1}{L_\alpha} \log P(y_1, \dots, y_L)$<br />$= \frac{1}{L_\alpha} \sum_{t=1}^L \log P(y_t | y_1, \dots, y_{t-1}, c)$<br />Often, $\alpha = 0.7$ | $O(knT)$                                   |

![Greedy Search](./../assets/image-20230527225713642.png){ loading=lazy }

![Beam Search](./../assets/image-20230527225932610.png){ loading=lazy }

## Disadvantage

Not suitable for large sentences, since the context vector might not be able to encapsulate the effect of very much previous words.
