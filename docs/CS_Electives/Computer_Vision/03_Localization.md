# Localization

Image --> Bounding box

![](assets/localization.png)

1. Train classification model
2. Attach new fully-connected "regression head"
3. Train regression head with regression loss
4. At inference, use both heads

## Regression Head Approaches
- Class agnostic: one box in total
- Class specific: one box per class
	- more intuitive
	- works for multiple object localization; For eg: represent human pose with $k$ joints

## Regression Head Position

![](assets/localization_regression_head_position.png)

## Sliding Window

### Naive
- Run classification + regression head at multiple location on high resolution network
- Combine classifier and regressor predictions across all scales for final prediction

![](assets/localization_sliding_window.png)

### Efficient

Convert FC layers into conv layers

![](assets/localization_efficient_sliding_window.png)

|                             |                                                      |
| --------------------------- | ---------------------------------------------------- |
| Train                       | ![](./assets/efficient_sliding_window_training.png)  |
| Inference<br>(larger image) | ![](./assets/efficient_sliding_window_inference.png) |

Advantage: Extra compute only for extra pixels