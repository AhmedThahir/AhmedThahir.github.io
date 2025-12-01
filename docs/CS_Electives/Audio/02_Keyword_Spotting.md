# Keyword Spotting

![](assets/keyword_spotting.png){ loading=lazy }

## Keyword Spotting vs Speed Recognition

|             | Keyword Spotting | Speed Recognition    |
| ----------- | ---------------- | -------------------- |
| Power-Usage | Low              | High                 |
| Type        | Continuous       |                      |
| Location    | On-Device        | On-Device/<br>Online |

## Types

|     | Single Shot         | Streaming                 |
| --- | ------------------- | ------------------------- |
|     | Only keyword spoken | Keyword within a sentence |

## Challenges

| Aspect               | Constraint      | Comment                                                                                         | Metrics                                         |
| -------------------- | --------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------- |
| System performance   | Latency         | Listening animation<br>                                                                         |                                                 |
|                      | Bandwidth       |                                                                                                 |                                                 |
| Preserving           | Security        | Safeguarding data being sent to cloud                                                           |                                                 |
|                      | Privacy         |                                                                                                 |                                                 |
| Model                | Accuracy        | Listen continuously, but only trigger at the right time<br><br>Pick operating point accordingly | ![](assets/keyword_spotting_accuracy_curve.png){ loading=lazy } |
|                      | Personalization | Trigger only for user, not for other users or for background noise                              |                                                 |
| Resource constraints | Battery         |                                                                                                 |                                                 |
|                      | Memory          |                                                                                                 | ![](assets/keyword_spotting_tinymy_memory.png){ loading=lazy }  |


## Model

Spectrogram is just an image

![](assets/keyword_spotting_flowchart.png){ loading=lazy }

### TinyConv

Since we only we are only focused on recognizing a few keywords, we can just use One Conv2D followed by single dense layer

```mermaid
flowchart LR

Input --> Conv --> FC --> Softmax --> Output
```

#### Limitations
- Limited vocabulary
- Lower accuracy
- Limited UX

## Cascading
![](assets/keyword_spotting_cascading.png){ loading=lazy }

![](assets/cascading_operating_curve.png){ loading=lazy }
## Multiple Inferences

- Average inferences across multiple time slices

This is to avoid False Positives for group of words. For eg:
- No
	- No good
	- Notion
	- Notice
	- Notable