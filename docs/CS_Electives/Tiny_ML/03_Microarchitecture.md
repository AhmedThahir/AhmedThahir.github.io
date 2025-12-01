# Microarchitecture

 - Arithmetic unit design
 - Memory organization

## Processing Element

Should support dot product

- Multiplier with 2 elements
- Accumulator with 2 elements

Accumulator: Adder that keeps result in storage

Inference in INT8 precision => Multipliers are INT8, because adders and accumulators need wide range to perform accurate accumulation of many numbers

![image-20240504170520675](./assets/image-20240504170520675.png){ loading=lazy }

### Sequential

| Step |                                                              |
| ---- | ------------------------------------------------------------ |
| 1    | ![image-20240504164012538](./assets/image-20240504164012538.png){ loading=lazy } |
| 2    | ![image-20240504164010093](./assets/image-20240504164010093.png){ loading=lazy } |

### Paralllel/Vectorized

| Step |                                                              |
| ---- | ------------------------------------------------------------ |
| 1    | ![image-20240504164302118](./assets/image-20240504164302118.png){ loading=lazy } |
| 2    | ![image-20240504164213548](./assets/image-20240504164213548.png){ loading=lazy } |

### Pipelined

Initiation interval: How often we can start computation of a new element in a loop

Break down computation into multiple steps with intermediate registers

### Interleaved

![image-20240504170328804](./assets/image-20240504170328804.png){ loading=lazy }

## Precision

Block Floating Point

- One exponent for each exponent

![image-20240504170905075](./assets/image-20240504170905075.png){ loading=lazy }

## On-Chip Memory

Bit-width of address = no of data entries

Connecting RAM to MAC

|                                       |                                                              |                                                              |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Simple | | ![image-20240504172833548](./assets/image-20240504172833548.png){ loading=lazy } |
| Use separate memories for 2 operands |                                                              | ![image-20240504172818182](./assets/image-20240504172818182.png){ loading=lazy } |
| Increase no of read ports | Problems with adding many read ports to SRAM<br/><br/>1. Large size<br/>2. Inc power consumption<br/>3. Slow<br/>4. In FPGA, you need to duplicate your memorie | ![image-20240504172334439](./assets/image-20240504172334439.png){ loading=lazy } |
| Banking                               | Use multiple small memories | ![image-20240504173115539](./assets/image-20240504173115539.png){ loading=lazy } |

## Computing Paradigms

| Processing                         |                                                              | Why?                                                         |
| ---------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| In-Sensor                          | ![image-20240504173510262](./assets/image-20240504173510262.png){ loading=lazy } | Data movement from sensor to processor is costly<br /><br />For eg, if you only need class label as output, why unnecessarily transfer 8MP image to processor |
| Near-Memory                        | ![image-20240504175124559](./assets/image-20240504175124559.png){ loading=lazy } |                                                              |
| In-Memory<br />(Analog Processing) | ![image-20240504175143868](./assets/image-20240504175143868.png){ loading=lazy } | - Weights stored as charges<br />- Activations delivered as analog voltages<br />- By activating pre-charge circuity on the word & bit lines, we can perform multiplication between input activation voltage & stored weights |

