# TinyML

Rather than adding more compute power, focus on improving compute efficiency

Will mainly focus on the following applications: Speech, Computer Vision, NLP

## Topics

- Hardware
  - Architecture & Dataflow
  - Metrics and Analysis
  - Efficiency
  - Micro-architecture/Circuits
- Model Optimization
  - Quantization
  - Pruning
  - Knowledge distillation
  - AutoML
- Software: Optimize DNN operations through software compilation/kernel implementations
  - Domain-specific compilers; eg: TVM
  - Kernel implementations
  - Mapping onto hardware
- Systems
  - Pre/Post Processing
  - Distributed training
  - Federated learning
  - Environmental issues

## Pre-Requisites

- Computer archictecture
- Machine Learning
- Python programming
- PyTorch Basics

## Reading

|                              |                                                              |
| ---------------------------- | ------------------------------------------------------------ |
| Textbook                     | Efficient processing of deep neural networks                 |
| Introduction                 | A New Golden Age for Computer Architecture<br/>- [PDF](https://www.doc.ic.ac.uk/~wl/teachlocal/arch/papers/cacm19golden-age.pdf)<br/>- [HTML](https://cacm.acm.org/research/a-new-golden-age-for-computer-architecture/) |
| DNN Computations             | TB Chap 1, 2<br />[What’s the backward-forward FLOP ratio for Neural Networks?](https://www.google.com/url?q=https://www.lesswrong.com/posts/fnjKpBoWJXcSDwhZk/what-s-the-backward-forward-flop-ratio-for-neural-networks&sa=D&source=editors&ust=1713034382437594&usg=AOvVaw26KDyCesOXEh2QCWiV1V9p)<br/>[Optimizing RNNs in CuDNN 5<br/>](https://www.google.com/url?q=https://developer.nvidia.com/blog/optimizing-recurrent-neural-networks-cudnn-5/&sa=D&source=editors&ust=1713034382437678&usg=AOvVaw2wrzXKhnLzzhO1Zi4TcGsN)[What are keys, queries, and values in attention mechanisms?<br/>](https://www.google.com/url?q=https://stats.stackexchange.com/questions/421935/what-exactly-are-keys-queries-and-values-in-attention-mechanisms&sa=D&source=editors&ust=1713034382437708&usg=AOvVaw0V2mblgGBD4LouUazOwv2S)[Attention is all you need](https://www.google.com/url?q=https://arxiv.org/abs/1706.03762&sa=D&source=editors&ust=1713034382437736&usg=AOvVaw0MoLL8qxAddfp2qBe8yXXk) |
| Hardware                     | Book: Chapter 3<br/>[In-datacenter Performance Analysis of a Tensor Processing Unit](https://www.google.com/url?q=https://arxiv.org/pdf/1704.04760.pdf&sa=D&source=editors&ust=1713034382437992&usg=AOvVaw0EmsULCbIakfDb1t6kPHdX)<br/>Optional: Computer Architecture: A Quantitative Approach. Ch 7<br />Book: Chapter 5[<br/>](https://www.google.com/url?q=https://groq.com/wp-content/uploads/2020/06/ISCA-TSP.pdf&sa=D&source=editors&ust=1713034382438093&usg=AOvVaw1vJSIwdLYRh6vN4z3nX6p6)[Think Fast: a TSP for Accelerating Deep Learning Workload](https://www.google.com/url?q=https://groq.com/wp-content/uploads/2020/06/ISCA-TSP.pdf&sa=D&source=editors&ust=1713034382438160&usg=AOvVaw0w3uE1ueIsINMCrl6CGm2D)s<br/>[FYI: OCP Microscaling (MX) Format Specification](https://www.google.com/url?q=https://www.opencompute.org/documents/ocp-microscaling-formats-mx-v1-0-spec-final-pdf&sa=D&source=editors&ust=1713034382438192&usg=AOvVaw2clt-CFd71r7uU3P2410m2)<br />Book: Chapter 8[<br/>](https://www.google.com/url?q=https://www.microsoft.com/en-us/research/uploads/prod/2018/03/mi0218_Chung-2018Mar25.pdf&sa=D&source=editors&ust=1713034382438407&usg=AOvVaw3inG9Y93lf6Wp5q_cXT-j8)[Serving DNNs in Real-time with Project Brainwav](https://www.google.com/url?q=https://www.microsoft.com/en-us/research/uploads/prod/2018/03/mi0218_Chung-2018Mar25.pdf&sa=D&source=editors&ust=1713034382438440&usg=AOvVaw0LQc7I8kSAz5Q00DR1o82h)[e<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1602.01528.pdf&sa=D&source=editors&ust=1713034382438469&usg=AOvVaw31arQF_6uJBlt-uURQzaRK)[Ten Lessons from 3 Generations Shaped Google TPUv4i](https://www.google.com/url?q=https://www.gwern.net/docs/ai/2021-jouppi.pdf&sa=D&source=editors&ust=1713034382438494&usg=AOvVaw0REU79dd-910-Uth5yaKmq)<br/>Optional:[ ](https://www.google.com/url?q=https://arxiv.org/pdf/1602.01528.pdf&sa=D&source=editors&ust=1713034382438522&usg=AOvVaw3JIKIrKb25-s3WphSlO3FU)[EIE: Efficient Inference Engine on Compressed DNN](https://www.google.com/url?q=https://arxiv.org/pdf/1602.01528.pdf&sa=D&source=editors&ust=1713034382438547&usg=AOvVaw2Z25wzlBowxsTnAP0WSZBN)<br/>Optional[: ](https://www.google.com/url?q=https://arxiv.org/pdf/2007.00864.pdf&sa=D&source=editors&ust=1713034382438576&usg=AOvVaw394wfJT8CLKWjyFKsB9IMi)[Survey on sparse hardware acceleration](https://www.google.com/url?q=https://arxiv.org/pdf/2007.00864.pdf&sa=D&source=editors&ust=1713034382438600&usg=AOvVaw04aXvp7w4T2KNcNRXtd3lr) |
| Microarchitecture            | [Deep learning with INT8 on Xilinx devices](https://www.google.com/url?q=https://www.xilinx.com/support/documentation/white_papers/wp486-deep-learning-int8.pdf&sa=D&source=editors&ust=1713034382438780&usg=AOvVaw3ROpVzJZm7fqvwOHBUwX-C)<br/>[On-Chip Memory Design for Low-Power CNN Accelerators<br/>](https://www.google.com/url?q=https://drive.google.com/file/d/1kvlH6h8SGXOR9goXW2UInwcN8DWiisH6/view?usp%3Dsharing&sa=D&source=editors&ust=1713034382438818&usg=AOvVaw2VWBZ5mCMtlCJ-JwhHHKaS)Optional: [Making Floating Point Math Highly Efficient for AI Hardware<br/>](https://www.google.com/url?q=https://engineering.fb.com/2018/11/08/ai-research/floating-point-math/&sa=D&source=editors&ust=1713034382438856&usg=AOvVaw1cyN4m-i9kR0lNuUXqMv_m)Optional: Book: Chapter 10 |
| Quantization                 | Book: Chapter 7[<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1712.05877.pdf&sa=D&source=editors&ust=1713034382439155&usg=AOvVaw3-XFYjqUjdfGX2mPGaKpjH)[Quantization and Training of NNs for Efficient INT-only Inferenc](https://www.google.com/url?q=https://arxiv.org/pdf/1712.05877.pdf&sa=D&source=editors&ust=1713034382439185&usg=AOvVaw0W6k5W33XbioPQYfwcOD-e)e<br/>[Training DNNs with 8-bit Floating-Point Numbers](https://www.google.com/url?q=https://arxiv.org/pdf/1812.08011.pdf&sa=D&source=editors&ust=1713034382439219&usg=AOvVaw0qbj7_iTAtOCRtsYWUSCpa) |
| Pruning                      | Book: Chapter 8[<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1506.02626.pdf&sa=D&source=editors&ust=1713034382439361&usg=AOvVaw1dGzWBCN3hNP1bXb3_9P99)[Learning Both Weights and Connections for Efficient NN](https://www.google.com/url?q=https://arxiv.org/pdf/1506.02626.pdf&sa=D&source=editors&ust=1713034382439390&usg=AOvVaw3s6hFaxWPhc_dqN7Rs5zwF)s[<br/>The Lottery Ticket Hypothesi](https://www.google.com/url?q=https://arxiv.org/pdf/1803.03635.pdf&sa=D&source=editors&ust=1713034382439417&usg=AOvVaw19TNwoFQmUUhj-M5lUYwcS)s |
| TinyML                       | [TinyML Progress, Challenges and Roadmap](https://www.google.com/url?q=https://drive.google.com/file/d/158qUFD4cZhBVW1IbuC0Daci2k8nnFvp9/view?usp%3Dsharing&sa=D&source=editors&ust=1713034382439662&usg=AOvVaw3C8C2ML50l9wDO3tSyHkyK) |
| Knowledge Distillation       | [Distilling the Knowledge in a Neural Network<br/>Knowledge Distillation: A Survey](https://www.google.com/url?q=https://arxiv.org/pdf/1503.02531.pdf&sa=D&source=editors&ust=1713034382439795&usg=AOvVaw27zYhcJCFTfMECldwbMSeY) |
| Neural Architecture Search   | Book: Chapter 9<br/>[Neural Architecture Search with Reinforcement Learning](https://www.google.com/url?q=https://arxiv.org/pdf/1611.01578.pdf&sa=D&source=editors&ust=1713034382440047&usg=AOvVaw2HL80Am5LDjTJP8P8FainK)<br/>[BRP-NAS: Prediction-based NAS using GCNs<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/2007.08668.pdf&sa=D&source=editors&ust=1713034382440077&usg=AOvVaw1DPpQBogcsYgk9OFfShpE6)[AutoML Codesign of a CNN and its Hardware Accelerator](https://www.google.com/url?q=https://arxiv.org/pdf/2002.05022.pdf&sa=D&source=editors&ust=1713034382440100&usg=AOvVaw0FsjWq2TVwRjjJ3VBHCWal) |
| Kernel Computation           | Book: Chapter 4<br/>[Fast algorithms for CNNs<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1509.09308.pdf&sa=D&source=editors&ust=1713034382440277&usg=AOvVaw0bycctiXGhgD5JPFckGiIn)[End-to-end ASR Model Compression using Reinforcement Learning<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1907.03540.pdf&sa=D&source=editors&ust=1713034382440306&usg=AOvVaw24EnOg6zBavifB_iYmWOm9)Optional: [TNet](https://www.google.com/url?q=https://openaccess.thecvf.com/content_CVPR_2019/papers/Kossaifi_T-Net_Parametrizing_Fully_Convolutional_Nets_With_a_Single_High-Order_Tensor_CVPR_2019_paper.pdf&sa=D&source=editors&ust=1713034382440330&usg=AOvVaw1sailX8cu1cK0kArvz4Yl9) |
| Mapping                      | Book: Chapter 6<br/>[Optimizing RNNs on GPUs](https://www.google.com/url?q=https://developer.nvidia.com/blog/optimizing-recurrent-neural-networks-cudnn-5/&sa=D&source=editors&ust=1713034382440506&usg=AOvVaw2faYU2QG6iULJT-NItcat1)<br/>[DLA: Compiler and FPGA Overlay for DNN Inference Acceleration](https://www.google.com/url?q=https://arxiv.org/pdf/1807.06434.pdf&sa=D&source=editors&ust=1713034382440541&usg=AOvVaw26NEEPLUxuP6-3M7B9aXBA) |
| TVM                          | [TVM: An Automated Optimizing Compiler for Deep Learning](https://www.google.com/url?q=https://arxiv.org/pdf/1802.04799.pdf&sa=D&source=editors&ust=1713034382440566&usg=AOvVaw1yM8SofWWeF4KIufYCsu03) |
| Pre-/Postprocessing          | [AI Tax: The Hidden Cost of AI Data-Center Applications<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/2007.10571.pdf&sa=D&source=editors&ust=1713034382440813&usg=AOvVaw1ZlL8XCKbGhNGal4w-_41j)[Rethinking Data Storage and Preprocessing in Datacenters<br/>](https://www.google.com/url?q=https://www.sigarch.org/rethinking-data-storage-and-preprocessing-for-ml/&sa=D&source=editors&ust=1713034382440840&usg=AOvVaw3kgYbvkrMao3LJKROtAe56)[Faster Neural Networks Straight from JPEG](https://www.google.com/url?q=https://papers.nips.cc/paper/2018/file/7af6266cc52234b5aa339b16695f7fc4-Paper.pdf&sa=D&source=editors&ust=1713034382440881&usg=AOvVaw3HzxUvBYmkY5-gTgn2kmGo) |
| Distributed Training         | [Horovod: Fast and Easy Distributed Deep Learning in Tensorflow<br/>](https://www.google.com/url?q=https://arxiv.org/pdf/1802.05799.pdf&sa=D&source=editors&ust=1713034382441047&usg=AOvVaw0Q6KWZIyqmQUpUB7X4uVtj)[Large Scale Distributed Deep Learning](https://www.google.com/url?q=http://static.googleusercontent.com/media/research.google.com/en//archive/large_deep_networks_nips2012.pdf&sa=D&source=editors&ust=1713034382441075&usg=AOvVaw38xKquQxbaTrfj-uQxNp5_) |
| Federated Learning           | [Google AI Blog Post on FL<br/>](https://www.google.com/url?q=https://ai.googleblog.com/2017/04/federated-learning-collaborative.html&sa=D&source=editors&ust=1713034382441213&usg=AOvVaw18LXJRz-AaMdFDQs_dpj8V)[Communication Efficient Learning of DNNs from Decentralized Data](https://www.google.com/url?q=https://arxiv.org/pdf/1602.05629.pdf&sa=D&source=editors&ust=1713034382441243&usg=AOvVaw0wBKPtMCVkjVQszjvRhoVI)[<br/>Towards Federated Learning at Scale: System Design](https://www.google.com/url?q=https://proceedings.mlsys.org/paper/2019/file/bd686fd640be98efaae0091fa301e613-Paper.pdf&sa=D&source=editors&ust=1713034382441268&usg=AOvVaw2_Uydz8D9PaXtJyjdmmjFU) |
| Ethical/Environmental Issues | [Chasing Carbon: The Elusive Environmental Footprint of Computing](https://www.google.com/url?q=https://ugupta.com/files/ChasingCarbon_HPCA2021.pdf&sa=D&source=editors&ust=1713034382441512&usg=AOvVaw2E5z40WEuXS5cnIRGy9Xj4)<br/>[On the Dangers of Stochastic Parrots: Can Language Models be Too Big](https://www.google.com/url?q=https://dl.acm.org/doi/pdf/10.1145/3442188.3445922&sa=D&source=editors&ust=1713034382441544&usg=AOvVaw0b8JXYCGl4KyovzZLeSZN_)[<br/>](https://www.google.com/url?q=https://www.techrxiv.org/articles/preprint/The_Carbon_Footprint_of_Machine_Learning_Training_Will_Plateau_Then_Shrink/19139645/3&sa=D&source=editors&ust=1713034382441570&usg=AOvVaw1K8WxaunNQ0o04i9pjYP33)[The Carbon Footprint of ML Training will Plateau, then Shrink](https://www.google.com/url?q=https://www.techrxiv.org/articles/preprint/The_Carbon_Footprint_of_Machine_Learning_Training_Will_Plateau_Then_Shrink/19139645/3&sa=D&source=editors&ust=1713034382441603&usg=AOvVaw2pDQ9IkZe29YlMuBafTwu4) |

## References

- [x] Machine Learning Hardware and Systems (Cornell Tech, Spring 2022)
  - [x] [Videos](https://www.youtube.com/playlist?list=PL0mFAhrXqy9CuopJhAB8GVu_Oy7J0ery6)
  - [x] [Material](https://abdelfattah-class.github.io/ece5545/)
- [ ] [TinyML and Efficient Deep Learning Computing | EfficientML.ai - MIT HAN Lab](https://www.youtube.com/playlist?list=PL80kAHvQbh-pT4lCkDT53zT8DKmhE0idB)
- [ ] [Tiny Machine Learning | UPenn](https://www.youtube.com/playlist?list=PL7rtKJAz_mPe6kAbiH6Ucq02Vpa95qvBJ)
- [ ] [AutoDL | Applied Deep Learning](https://www.youtube.com/playlist?list=PLoEMreTa9CNnQXiups8QMzmyKe4b3ge6F)

