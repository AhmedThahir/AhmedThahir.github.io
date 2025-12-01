## Access Protocols

|                                 | Random-Access/<br />Contention                               | Controlled-Access                           | Channelization           |
| ------------------------------- | ------------------------------------------------------------ | ------------------------------------------- | ------------------------ |
|                                 | No station is superior to another<br />No station permits another station to send at the same time<br />Node with packet transmits at full channel data rate<br />All transmission on shared channel |                                             |                          |
| Collisions                      | Moderate                                                     | Little-to-none                              |                          |
| Throughput for smaller networks | Low                                                          | High                                        |                          |
| Throughput for larger networks  | High                                                         | Low                                         |                          |
| Easy to maintain?               | ✅                                                            | ❌                                           |                          |
| Commonly-used?                  | ✅                                                            | ❌<br />(Hard to control large networks)     |                          |
| Example                         | ALOHA<br />CSMA<br />CSMA/CD<br />CSMA/CA                    | Reservation<br />Polling<br />Token-Passing | FDMA<br />TDMA<br />CDMA |

## Collision

When 2 nodes transmit concurrently

## Carrier-Sensing

When the energy level is higher than usual, that means that there is a collision

![image-20230405005937534](./assets/image-20230405005937534.png){ loading=lazy }

However, this method may not suitable for wireless transmission, due to energy loss.

## Persistence Methods

|                     | 1-persistent                                                 | Non-persistent                                               | $p$ - persistent                                             |
| ------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
|                     | Default persistent method                                    |                                                              | Probabilistic mixture of 1-persistent & non-persistent<br />Assume channels are slotted<br />One slot = contention period (one RTT)<br />Used when time slot duration $\ge$ max $T_P$ |
| Steps               | 1. Sense channel<br />2. if idle, transmit immediately<br />3. If busy, keep listening | 1. Sense channel<br />2. If idle, transmit immediately<br />3. If busy, wait random amount of time and sense channel | - When station ready to send, it senses the channel<br/>- If channel is idle, transmits with probability $pp$<br/>- If channel is busy, station waits until next slot.<br />- With probability $q=l-p$, the station then waits for beginning of next slot <br/>- If next slot also idle, either transmit/wait again with probabilities $pp$ & $q$ <br/>- Process repeated till either frame transmitted/another station starts transmitting<br/>- If another station  transmits, station waits random amount of time & starts again |
| If collision occurs | Wait ranom amount of time & start over                       | Wait random amount of time & start over                      |                                                              |
| Diagram             | ![image-20230405002625651](./assets/image-20230405002625651.png){ loading=lazy } | ![image-20230405002816818](./assets/image-20230405002816818.png){ loading=lazy } | ![image-20230405003042180](./assets/image-20230405003042180.png){ loading=lazy } |

![image-20230405003458524](./assets/image-20230405003458524.png){ loading=lazy }
