# Transformers

## Spatial Transformer

Differentiable (and hence, learnable) way of cropping images, allowing attention models to attend to arbitrary regions

![](assets/spatial_transformer_block_diagram.png){ loading=lazy }

![](assets/transformers_intuition.png){ loading=lazy }

1. Function mapping pixel coordinates $(x_t, y_t)$ of output to pixel coordinates $x_s, y_s$ of input
	1. ![](assets/transformers_attention_mapping.png){ loading=lazy }
2. Repeat for all pixels in output to get a sampling grid
3. Use bilinear interpolation to compute output

![](assets/spatial_transformer_outcome.png){ loading=lazy }

## LLM Transformer

![](assets/transformer_architecture.png){ loading=lazy }

