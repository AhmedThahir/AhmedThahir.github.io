# Decentralized Learning

## Types

|                      | Distributed         | Offloading                                                   | Federated                                                    | Collaborative Learning                                       |
| -------------------- | ------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
|                      |                     | Send data to central server for training<br />Device only used as sensor | - Data never stored in data center<br/>- Encrypt data and only decrypt after averaging 1000 updates | Each device maintains functional model                       |
| Model Location       | Servers             | Servers                                                      | Device<br />Aggregated on Cloud                              | Device                                                       |
| Data Location        | Device<br />Servers | Device<br />Servers                                          | Device                                                       | Device                                                       |
| Design goals         | Speed               |                                                              | Privacy<br />Online learning<br />Security<br />Scale        |                                                              |
| Device Types         | Same                |                                                              | Different                                                    |                                                              |
| Device Compute Power | High                |                                                              | Low                                                          |                                                              |
| Training             | Complex             |                                                              | Simple                                                       |                                                              |
|                      |                     |                                                              | Run training when phone charging<br />Transmit updates when WiFi available |                                                              |
| Training examples    |                     |                                                              | Next-word prediction                                         |                                                              |
| Number of devices    | 10-1k               |                                                              | 100k+                                                        |                                                              |
| Network speed        | Fast                |                                                              | Slow                                                         |                                                              |
| Network reliability  | Reliable            |                                                              | Intermittent                                                 |                                                              |
| Data Distribution    | IID                 |                                                              | Non-IID<br />(Each device has own data distribution)<br />Not representative of training data |                                                              |
| Applications         |                     |                                                              | - Privacy<br/>  - Personal data from devices<br/>  - Health data from hospitals<br/>- Continuous data<br/>  - Smart home/city<br/>  - Autonomous vehicles |                                                              |
| Advantages           |                     | - Save device battery<br/>- No need to support on-device training<br/>- Better accuracy? |                                                              | - Most secure: Data never aggregated to a central server that could be compromised<br/>- Most scalable: No central server with bandwidth limitations |
| Limitations          |                     | - poor privacy<br/>- worse scalability                       | Not fully private: ==You can recover data from model parameters/gradient updates==<br />Consumes higher total energy |                                                              |
| Challenges           |                     |                                                              | Poor network                                                 | All challenges of FL                                         |
| Example              |                     | Google Photos                                                |                                                              |                                                              |
|                      |                     |                                                              | ![Federated learning - Wikipedia](./assets/Centralized_federated_learning_protocol-20240518105026703.png){ loading=lazy } |                                                              |

### Terms

|                |                                                              |
| -------------- | ------------------------------------------------------------ |
| Straggler      | Device that doesn’t return data on time                      |
| Data Imbalance | One devices has 10k samples, while 10k devices have 1 sample each |

## Terms

### Compression

- Gradient
- Data

### Quantization

Quantization to gradients before transmission

Communication cost drops linearly with bit width

### Pruning

Prune gradients based magnitude and compress zeroes

## Distributed Training

- Model Parallelism: Fully-Connected layers
- Data Parallelism: Convolutional layers

### Single GPU-system

![image-20240517234025669](./assets/image-20240517234025669.png){ loading=lazy }

### Model Parallelism

All workers train on same batch

Workers communicate as frequently as network allows

![image-20240517234511422](./assets/image-20240517234511422.png){ loading=lazy }

Necessary for models that do not fit on a single GPU

No method to hide synchronization latency

- Have to wait for data to be sent from upstream model split
- Need to think about how pipelining would work for model-parallel training

Types

- Inter-layer
- Intra-layer

Limitations

- Overhead due to
  - moving data from one GPU to another via CPU
  - Synchronization
- Pipelining not easy

### Data Parallelism

Each worker trains the same convolutional layers on a different data batch

Workers communicate as frequently as network allows

|                           |                                                                  |                                                                                                     | Communication Overhead                | Advantage                                           | Limitation|
|---                        | ---                                                              | ---                                                                                                 | ---                                   | ---                                                 | ---|
|Single-GPU                 | ![image-20240517235009471](./assets/image-20240517235009471.png){ loading=lazy } |                                                                                                     |                                       |                                                     | |
|Multiple GPU               | ![image-20240517235030466](./assets/image-20240517235030466.png){ loading=lazy } | Average gradients across minibatch on all GPUs<br />Over PCIe, ethernet, NVLink depending on system | $kn(n-1)$                             |                                                     | High communication overhead|
|Parameter Server           | ![image-20240517235720628](./assets/image-20240517235720628.png){ loading=lazy } |                                                                                                     |                                       |                                                     | |
|Parallel Parameter Sharing | ![image-20240517235936682](./assets/image-20240517235936682.png){ loading=lazy } |                                                                                                     | $k$ per worker<br />$kn/s$ for server |                                                     | |
|Ring Allreduce             | ![image-20240518000314126](./assets/image-20240518000314126.png){ loading=lazy } | Each GPU has different chunks of the mini-batch | $2k\dfrac{n-1}{n}$                    | Scalable<br />Communication cost independent of $n$ | |

where
- $n=$ no of client GPUs
- $k =$ no of gradients
- $s=$ no of server GPUs

#### Ring-Allreduce

![image-20240518000113085](./assets/image-20240518000113085.png){ loading=lazy }

##### Step 1: Reduce-Scatter

![image-20240518081322299](./assets/image-20240518081322299.png){ loading=lazy }

![image-20240518081334451](./assets/image-20240518081334451.png){ loading=lazy }

##### Step 2: Allgather

![image-20240518081605306](./assets/image-20240518081605306.png){ loading=lazy }

### Weight Updates Types

|                      | Synchronous | Asynchronous                                                 |
| -------------------- | ----------- | ------------------------------------------------------------ |
| Working              |             | - Before forward pass, fetch latest parameters from server<br/>- Compute loss on each GPU using these latest parmeters<br/>- Gradients sent back to server to update model |
| Speed per epoch      | Slow        | Fast                                                         |
| Training convergence | Fast        | Slow                                                         |
| Accuracy             | Better      | Worse                                                        |
|                      |             | ![image-20240518082907733](./assets/image-20240518082907733.png){ loading=lazy } |

### Pipeline Parallelism

![image-20240518084125383](./assets/image-20240518084125383.png){ loading=lazy }

## Federated Learning

“Federated”: Distributed but “report to” one central entity

Conventional learning

- Data collection
- Data Labeling (if supervised)
- Data cleaning
- Model training

But new data is generated very frequently

### Steps

1. Download model from cloud to devices
2. Personalization: Each device trains model on its own local data
3. Devices send their model updates back to server
4. Update global model
5. Repeat steps 1-4

Each iteration of this loop is called “round” of learning

### Algorithms


|             |                                                              | Handling Stragglers | Handling Data Imbalance                                      |                                                              |
| ----------- | ------------------------------------------------------------ | ------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| FedAvg      | The more data points a device has, the higher weight of device in updating global model | Drop                | Poor                                                         | ![image-20240518090015465](./assets/image-20240518090015465.png){ loading=lazy } |
| FedProx     |                                                              | Use partial results | Discourage large weight updates through regularization<br />$\lambda {\vert \vert w' - w \vert \vert}^2$<br />$w=$ Weight of single device |                                                              |
| q-fed-avg   |                                                              |                     | Discourage large weight updates for any single device        |                                                              |
| per-per-avg |                                                              |                     |                                                              |                                                              |

![image-20240518091433814](./assets/image-20240518091433814.png){ loading=lazy }

### Data Labelling

How to get labels

- Sometimes explicit labeling not required: Next-work prediction
- Need to incentivize users to label own data: Google Photos
- Use data for unsupervised learning

### Types

|            |                                                              |
| ---------- | ------------------------------------------------------------ |
| Horizontal | ![img](./assets/Classification-of-federated-learning.png){ loading=lazy }    |
| Vertical   | ![Classification-of-federated-learning copy](./assets/Classification-of-federated-learning%20copy.png){ loading=lazy } |
| Transfer   | ![Classification-of-federated-learning copy 2](./assets/Classification-of-federated-learning%20copy%202.png){ loading=lazy } |
