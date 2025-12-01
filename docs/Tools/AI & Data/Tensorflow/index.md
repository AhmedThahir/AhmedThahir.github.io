# TensorFlow

![](assets/tensorflow_ecosystem.png){ loading=lazy }

![](assets/tfl_architecture.png){ loading=lazy }

|                                    | TensorFlow         | TensorFlow Lite                                                     | TensorFlow Lite Micro          | TensorFlow.js     | TensorFlow<br>Serving |
| ---------------------------------- | ------------------ | ------------------------------------------------------------------- | ------------------------------ | ----------------- | --------------------- |
| Usage                              | Model development  | Deployment to Microprocessors (laptops, mobile phones, RaspberryPi) | Deployment to Microcontrollers | Deployment to Web | Cloud, On-Prem        |
| Optimized for                      | X86<br>TPU<br>GPU  | X86<br>ARM Cortex A                                                 | ARM Cortex M<br>DSP<br>MCU     | Browser & Node    |                       |
| User                               | ML researcher      | Application developer                                               |                                |                   |                       |
| Supports training                  | ✅                  | ❌                                                                   | ❌                              | ❌                 | ❌                     |
| Supports inference                 | ✅<br>(inefficient) | ✅                                                                   | ✅                              | ✅                 | ✅                     |
| No of ops supported                | ~1400              | ~130                                                                | ~50                            |                   |                       |
| Native quantization support        | ❌                  | ✅                                                                   | ✅                              |                   |                       |
| OS-independent<br>(No OS required) | ❌                  | ❌                                                                   | ✅                              |                   |                       |
| Memory mapping of models           | ❌                  | ✅                                                                   | ✅                              |                   |                       |
| Delegation to accelerators         | ✅                  | ✅                                                                   | ❌                              |                   |                       |
| Distributed compute                | Needed             | Not needed                                                          | Not needed                     |                   |                       |
| Binary size                        | > 3MB              | 100KB                                                               | ~10KB                          |                   |                       |
| Base memory footprint              | ~ 5MB              | 300KB                                                               | 20KB                           |                   |                       |
| Weights                            | Variable           | Fixed                                                               |                                |                   |                       |
| Topology<br>Neuron connections     | Variable           | Fixed                                                               |                                |                   |                       |

## References

- [ ] [Learn TensorFlow and Deep Learning (beginner friendly code) | Daniel Bourke](https://www.youtube.com/playlist?list=PL6vjgQ2-qJFfU2vF6-lG9DlSa4tROkzt9)
- [ ] [Python in Data Science for Advanced - Deep Learning with Keras & TensorFlow | LearnDataa](https://www.youtube.com/playlist?list=PLXovS_5EZGh5mumJObZbMwQsDnscuP-z4)
- [ ] [Zero to Deployment - Deep Learning | Aladdin Persson](https://www.youtube.com/playlist?list=PLhhyoLH6Ijfwo42qqGo55-xn6p3uoEJeX)
