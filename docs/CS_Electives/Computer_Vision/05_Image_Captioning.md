# Image Captioning

![](assets/Image_Captioning_flowchart.png){ loading=lazy }

Rather than instructing the RNN to sample text at random, we are conditioning that sampling by the output of the CNN

## Forward Pass

![](assets/Image_Captioning_forward_pass_1.png){ loading=lazy }

![](assets/Image_Captioning_forward_pass_2.png){ loading=lazy }

![](assets/Image_Captioning_forward_pass_3.png){ loading=lazy }

### Backward pass

- If you start with pre-trained CNN, only backprop for the RNN
- Else, backprop through the RNN and CNN
