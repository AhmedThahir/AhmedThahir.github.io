# Deep Learning

Deep Learning is subset of machine learning, which involves a deep neural network. Large availability of data in present-day has led to the rise in demand for deep learning applications.

Refer [Machine Learning](./../Machine_Learning/) concepts, to understand this course well.

## Types

```mermaid
flowchart TB
DL --> gm[Generative<br/>Models] & ha[Hybrid<br/>Architecture] & dm[Discriminative<br/>Models]

gm --> dbn[Deep<br/>Belief<br/>Networks] & da[Deep<br/>Autoencoder] & dbm[Deep<br/>Boltzmann<br/>Machine]

ha --> dnn[Deep<br/>Neural<br/>Networks]

dm --> cnn[Convolutional<br/>Neural<br/>Network] & dsn[Deep<br/>Stacking<br/>Networks]
```

## Applications of DL

- Object detection/counting
- Image/Video
  - classification
  - segmentation
  - captioning
  - sentence matching
  - face recognition
- Natural language processing
  - At the time of writing this sentence, ChatGPT’s successor GPT4 has come out, and it looks pretty insane

## Advantages

1. Flexible
2. Automatic
3. Robust
4. Generalizable
5. Parallelizable $\implies$ Scalable

## Disadvantages

1. Low interpretability (Black box)
2. Too many hyperparameters
3. Tend to overfit; poor generalizability
4. Require lot of data
5. Computationally-expensive wrt to [Resource Constraints](#Resource-Constraints)

## Resource Constraints

1. Processor Speed
2. Memory Size
3. Power Consumption

## Challenges

- Difficult for generalization
- Difficult for efficient optimization
- Lack of adequate data (addressed through [Transfer Learning](#Transfer Learning), Shallow learning, Incremental learning)
- Data inconsistencies
- Low battery life of edge devices (h/w controlling data flow at boundary b/w 2 networks)
- Resource-constrained algorithm development issues
- Diversity in computing units
- Privacy & security concerns (addressed through Encryption)

## Why Deep Learning?

- Deep networks can represent complex functions with fewer parameters
- Each layer of the network learns a “representation”

![image-20240710182558173](./assets/image-20240710182558173.png){ loading=lazy }

![image-20240710182724425](./assets/image-20240710182724425.png){ loading=lazy }

## Image Representation

Every images is a matrix of pixels, where each pixel is represented as a combination of **red, green, blue**; usually as a 8-bit value (0-255)

So if the width and height of image are $w, h$

## Key Metrics

- Accuracy
- Throughput
- Latency
- Energy efficiency
- Hardware costs
- Flexibility

## Popular Datasets

| Dataset  | Sample Size | Content                                                                    | Classes |
| :------: | ----------: | -------------------------------------------------------------------------- | :-----: |
|  MNIST   |      50,000 | Images of handwritten digits (0-9)                                         |   10    |
|  CIFAR   |      60,000 | Airplanes, cars, birds, cats, deer, dogs, frogs, horses, ships, and trucks |   10    |
| ImageNet |             |                                                                            |         |

## Transfer Learning

![image-20230527151527131](./../assets/image-20230527151527131.png){ loading=lazy }


| ......... Similarity<br>Size | Similar                  | Different            |
| ---------------------------- | ------------------------ | -------------------- |
| Little                       | Linear Classifier on FC7 | Not optimal          |
| Large                        | Finetune few layers      | Finetune more layers |

## Why Deep Learning?

Deep networks

1. empirically work better for a given parameter count
2. provably more efficient at representing functions that neural networks cannot actually learn (such as odd/even parity)

