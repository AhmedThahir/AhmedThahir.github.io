# Kernel Computation

### Machine Learning Software Stack

PyTorch is just a wrapper for writing CuDNN/MKL-DNN code

![image-20240517093631569](./assets/image-20240517093631569.png){ loading=lazy }

## Kernel Implementations

|                               |                                                              |                                                              | Limitation                                                   |
| ----------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Im2col                        | Convert image windows to columns of matrix<br /><br />or<br /><br />Replicate weights instead and flatten image<br /><br />Use to implement convolution as matrix multiplication | ![image-20240517094546145](./assets/image-20240517094546145.png){ loading=lazy } | Data replication at algorithmic level may increase demand for external memory bandwidth |
| Strassen’s MM transform       | Reduce no of multiplications in MM through reorganizing operations and offline computation |                                                              | Transform limitation                                         |
| Winograd Conversion transform | Reduce no of multiplications in Conv through reorganizing operations and offline computation<br /><br />Specific to<br />- supported filter size<br />- tile size of input |                                                              | Transform limitation                                         |
| Alpha Tensor                  |                                                              |                                                              |                                                              |
| FFT-Transform                 | Conv becomes multiplication<br />Filter needs to zero-pad to ensure same size as output<br /><br />Only useful for filter size >= log of output size for effectiveness, else IFFT overhead exceeds the gain |                                                              | Transform limitation<br />IFFT is costly overhead            |
| Log-domain multiplication     | $ab = 2^x 2^y = 2^{x+y} = 2^z$<br />Only convert magnitude of numbers<br />Compute sign using small circuit<br />$s_c = s_a \oplus s_b$ |                                                              | Finding log & exponents at high precision is expensive<br />No straightforward add operation in log domain |

Transform limitation: Requires transform to be performed at high precision to avoid accuracy detoriation

## Low-Rank Approximation

|                                   |                                                       |
| --------------------------------- | ----------------------------------------------------- |
| SVD: Singular Value Decomposition | $M = U \Sigma V$<br />Speedup = $\dfrac{mn}{k (m+n)}$ |
| Tensor decomposition              | Tucker Decomposition<br />Canonical Polyadic          |
