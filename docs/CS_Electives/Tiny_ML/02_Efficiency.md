# Efficiency

All elements on a single layer of a network are parallelizable

## CPU Chip Area

![image-20240416121111928](./assets/CPU_Chip_Area.png){ loading=lazy }

## Hardware Types

| Purpose     |                                                              |                                |
| ----------- | ------------------------------------------------------------ | ------------------------------ |
| General     | CPU (Central Processing Unit)                                | Low Latency<br />Control Flow  |
|             | GPU (Graphics Processing Unit)                               | High throughput<br />Data flow |
|             | TPU (Tensor Processing Unit)<br />NPU (Neural Processing Unit) |                                |
| Specialized | FPGA (Field Programmable Gate Assembly)                      | Re-Programmable Logic          |
|             | ASIC (Application Specific Integrated Circuit)               | Fixed logic                    |

## Performance Metrics

|          | Metric                |                                                  |              | Common Units | Affected by Hardware | Affected by DNN |
| -------- | --------------------- | ------------------------------------------------ | ------------ | ------------ | -------------------- | --------------- |
| Compute  | FLOPs/s               | **F**loating-point **op**erations per **s**econd |              |              | ✅                    | ❌               |
|          | OPs/s                 | Non floating-point **op**erations per **s**econd |              |              | ✅                    | ❌               |
|          | MACs/s                | Multipy-Accumulate Ops/s                         | Half FLOPs/s |              | ✅                    | ✅               |
|          | Latency               | No of sec per operation                          |              | s            | ✅                    | ✅               |
|          | Throughput            | No of operations per second                      |              | Ops/s        | ✅                    | ✅               |
| Memory   | Capacity              |                                                  |              | GB           | ❌                    | ❌               |
|          | Bandwidth             |                                                  |              | GB/s         | ❌                    | ❌               |
| Workload | Operational intensity |                                                  |              | Op/B         | ❌                    | ✅               |
|          | HW Utilization        |                                                  |              |              | ✅                    | ✅               |

### OPs

$$
\begin{aligned}
&\text{OPs} \\
&= \text{Ops/sec} \\
&=
\underbrace{
\dfrac{1}{\text{Cycles/Op}}
\times
\text{Cycles/sec}
}_\text{for single PE}
\times
\text{No of PEs}
\end{aligned}
$$

PE = Processing Element

### Roofline Plot

Characterize performance of given hardware device across different workloads, to help identify if a workload is memory-bound or compute-bound

![image-20240416134952487](./assets/image-20240416134952487.png){ loading=lazy }

| Speed up      | Technique                                       |                                                              |
| ------------- | ----------------------------------------------- | ------------------------------------------------------------ |
| Memory-bound  | Algorithmic improvement<br />(reduce precision) | ![image-20240416135852498](./assets/image-20240416135852498.png){ loading=lazy } |
|               | Faster memory chip                              | ![image-20240416135930083](./assets/image-20240416135930083.png){ loading=lazy } |
| Compute-Bound | Faster PE<br />(Overclocking)                   | ![image-20240416141235433](./assets/image-20240416141235433.png){ loading=lazy } |

### Operational Intensity

$$
\begin{aligned}
\text{Operational Intensity} &= \dfrac{\text{No of Ops}}{\text{Mem Footprint}} \\
\text{No of Ops} &= \text{Multiplications} + \text{Additions} \\
\text{Mem Footprint} &= \text{Size of parameters} + \text{Size of activations}
\end{aligned}
$$

Quantifies the ratio of computations to memory footprint of a DNN

The same DNN can have different operational intensity on different hardware, if each device supports different numerical precision (Size of data affects operational intensity)

![image-20240416141806577](./assets/image-20240416141806577.png){ loading=lazy }

### IDK

![image-20240416142904897](./assets/image-20240416142904897.png){ loading=lazy }

## Performance Bottlenecks

- Memory access efficiency
  - Uncoalesced reads
- Compute utilization
  - Overhead of control logic
- Complex DNN topologies
  - Control flow and data hazards may stall execution even if hardware is available

## Hardware Efficiency

### Energy breakdown

![img](./assets/energy_breakdown.png){ loading=lazy }

## Hardware Efficiency Approaches

| Approach                                                     | Technique                                          |                                                              |                                                              |
| ------------------------------------------------------------ | -------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Arithmetic                                                   | Specialized instructions                           | Amortize overhead<br />Reduce overhead fraction<br /><br />Perform complex/fused operations with the same data fetch<br />SIMD<br /><br />Matrix Multiple Unit<br /><br />HFMA<br />HDP4A<br />HMMA | ![image-20240416143737789](./assets/image-20240416143737789.png){ loading=lazy } |
|                                                              | Quantization                                       | Lower numerical precision                                    | ![image-20240418115326365](./assets/image-20240418115326365.png){ loading=lazy } |
| Memory                                                       | Locality                                           | Move data to inexpensive on-chip memory                      | ![image-20240418115729873](./assets/image-20240418115729873.png){ loading=lazy }<br />![image-20240418120330868](./assets/image-20240418120330868.png){ loading=lazy } |
|                                                              | Re-use                                             | Avoid expensive memory fetches<br /><br />Temporal: Read once, use same data multiple times by same PE<br />SIMD, SIMT<br /><br />Spatial: Read once, use data multiple times by multiple PEs<br />Dataflow processing<br /><br />Weights stationary (CNNs)<br />Input stationary (Fully-Connected Layers)<br />Output stationary | ![image-20240418120607987](./assets/image-20240418120607987.png){ loading=lazy }<br />![image-20240418140557228](./assets/image-20240418140557228.png){ loading=lazy }<br />![image-20240418140811910](./assets/image-20240418140811910.png){ loading=lazy } |
| Operations                                                   | Sparsity                                           | Skip ineffectual operations<br /><br />Activation Sparsity (Sparse Activation Functions: ReLU)<br />Weight Sparsity (Regularization/Pruning)<br />Block Sparsity<br /><br />Coarse-grained<br />Fine-grained - Overhead |                                                              |
|                                                              | Interleaving                                       |                                                              |                                                              |
| Model storage                                                | CSC Representation<br />(Compressed Sparse Column) |                                                              |                                                              |
| Model Optim:<br />Change DNN arch (and hence workload) to better fit HW | Compression                                        |                                                              |                                                              |
|                                                              | Distillation                                       |                                                              |                                                              |
|                                                              | AutoML                                             |                                                              |                                                              |

Floating-point `add` is more expensive relative to `integer`, compared to multiplication , due to shifting operations

## Guidelines for DSAs

Domain-Specific Architectures

- Dedicated memory to minimize distance of data transfer
- Invest resources saved from dropping advanced micro-architectural optimizations into more arithmetic units/larger memories
- Use easiest form of parallelism that matches the domain
- Reduce data size and type to simplest needed for the domain
- Use domain-specific programming language to port code to DSA
