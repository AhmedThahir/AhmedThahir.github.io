It is a combination of hardware, software, and firmware (software for hardware)

It is implemented in NIC and attaches into host’s system buses

## Sublayers

The data link may be further divided into sublayers, which is explained in detail in [Ethernet](08_Ethernet.md)

## Flow Control

Handles mismatch b/w sender’s and receiver’s speed

| Control Method                    | Type     | Meaning                           |
| --------------------------------- | -------- | --------------------------------- |
| Feedback-Based<br />(More common) | Explicit | Permission required from receiver |
| Rate-Based                        | Implicit | Limit sending rate                |

## Error Types

| Type         | No of Bits | Consecutive Bits? |
| ------------ | :--------: | :---------------: |
| Single-Bit   |     1      |                   |
| Multiple-Bit |     >1     |         ❌         |
| Burst        |     >1     |         ✅         |

## Error Control

|                                                          |                                                              |
| -------------------------------------------------------- | ------------------------------------------------------------ |
| Error detection codes                                    | Detect error                                                 |
| Error/Forward<br />correction codes<br />(FEC)           | Detect & correct error<br />Use in wireless networks         |
| Retransmission/<br />Automatic Repeat Request<br />(ARQ) | Used along with error detection/correction<br />Block of data with error discarded<br />Transmitter retransmits that block of data |

### Redundancy

Redundant bits added to data to detect & correct errors

```mermaid
flowchart LR

subgraph s[Sender's Encoder]
m1[Message] -->
Generator -->
a[Message &<br/>Redundancy]
end

subgraph r[Receiver's Decoder]
d[Received<br/>Data] -->
c[Checker] -->
|Accept| m2[Message]
end

a -->
|Unreliable<br/>Transmission| d

c --> |Discard| Lost
```

### Coding

Process of adding redundancy for error detection/correction

Error-detecting code can detect  only types of errors for which it is designed;
other types of errors may remain undetected.
There is no way to detect every possible error

| Code          | Steps                                                        | Redundant bits | Total bits<br />$n$ | Memoryless?                                     |
| ------------- | ------------------------------------------------------------ | :------------: | :-----------------: | ----------------------------------------------- |
| Block         | Divide data into set of $k$-bit blocks (called datawords) <br />Extra info attached to each block<br />Combined blocks called codewords |      $r$       |        $k+r$        | ✅                                               |
| Convolutional | Treats data a series of bits<br />Computes code over continuous series |                |                     | ❌<br />(Code depends on current & previous i/p) |

![image-20230404220718845](./assets/image-20230404220718845.png){ loading=lazy }

```mermaid
flowchart TB

d1["Dataword<br/>a3 a2 a1 a0<br/><br/>(k bits)"] -->
c1 & g["Generator<br/>(r bits)"]

g -->
c1["Codeword<br/>a3 a2 a1 a0 <span style='color:red'>p0</span><br/><br/>(n bits)"]
```

### Code Rate

$$
= \frac{k}{n}
$$

|  Code Rate   | $\implies$ | Error Correcting Capability | Bandwidth Efficiency |
| :----------: | :--------: | :-------------------------: | :------------------: |
|  $\uparrow$  |            |        $\downarrow$         |      $\uparrow$      |
| $\downarrow$ |            |         $\uparrow$          |     $\downarrow$     |

## Error Detection Methods

If syndrome = 0 at the receiver, there is no error

|                    | Simple parity check                                          | Horizontal & Vertical<br />Parity check                      | CRC<br />(Cyclic Redundancy Check)                           | Checksum                                                     |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
|                    | Use an odd/even parity bit                                   | Use parity bit vertically and horizontally                   | Add $r$ zeros to right of dividend, where $r=$no of redundant bits = length of divisor - 1<br />Long division using **XOR** | (used in network layer)<br />Find sum of digits<br />If overflow, perform padding<br />Take 1s complement |
| Errors detectable  | $\{1, 3, \dots, 2n+1 \}$<br />(odd no of errors)             | $\{1, 2, 3, 5, 6, 7, \dots \} \implies R - \{4n\}$           | All                                                          | All                                                          |
| Can correct error? | ❌<br />(error can be in any position<br />including parity bit itself) |                                                              |                                                              |                                                              |
|                    | ![image-20230404221704670](./assets/image-20230404221704670.png){ loading=lazy } | ![image-20230404222907397](./assets/image-20230404222907397.png){ loading=lazy } | ![image-20230404223404139](./assets/image-20230404223404139.png){ loading=lazy } |                                                              |

### Simple Parity

| Parity | Parity bit = 0 means dataword has |
| ------ | --------------------------------- |
| Odd    | Odd number of ones                |
| Even   | Even number of ones               |

## Mac Layer Throughput

Number of bits sent by MAC (Data Link) layer in given period of time

$$
\begin{aligned}
\text{Throughput} = \frac{\text{Payload}}{\text{Total Time}}
\end{aligned}
$$

## Control Frame

Frames that only contain headers/trailers, and no payload
