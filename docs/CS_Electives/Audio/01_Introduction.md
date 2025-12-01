# Introduction

## Human Perception of Sound

![](assets/Human_Perception_of_Sound.png){ loading=lazy }

## Dataset

### Building
- Who are the users
- What do they need
- What task are they trying to solve
- How do they interact with the system
	- Distance
	- Environment
		- Background Noise
		- Reverb
- Quality Control
	- Only keep whatever a human can understand
### Industry-Standard
- Google Speed Commands dataset
	- Recorded as individual words, not sentences
	- 1000-4000 examples of each word

![](assets/google_speech_commands_dataset.png){ loading=lazy }
 
## Good Characteristics of Model

|                   |                                         |
| ----------------- | --------------------------------------- |
| Volume Invariance | ![](assets/audio_volume_invariance.png){ loading=lazy } |
|                   |                                         |

## Pre-Processing

What aspects of the signal should you sent to the neural network

1. Align on start point
2. Normalization of amplitude
3. Denoise
4. Convert to frequencies, using Fast Fourier transform
	1. Extract features
	2. Sliding window
5. Cut on end point

| Word | Volume | Waveform                           | Spectrogram                           | MFCC                          |
| ---- | ------ | ---------------------------------- | ------------------------------------- | ----------------------------- |
| Yes  | Loud   | ![](assets/yes_loud_waveform.png){ loading=lazy }  | ![](assets/yes_loud_spectrogram.png){ loading=lazy }  | ![](assets/yes_loud_mfcc.png){ loading=lazy } |
|      | Quiet  | ![](assets/yes_quiet_waveform.png){ loading=lazy } | ![](assets/yes_quiet_spectrogram.png){ loading=lazy } |                               |
| No   | Loud   | ![](assets/no_loud_waveform.png){ loading=lazy }   | ![](assets/no_loud_spectrogram.png){ loading=lazy }   | ![](assets/no_loud_mfcc.png){ loading=lazy }  |
|      | Quiet  | ![](assets/no_quiet_waveform.png){ loading=lazy }  | ![](assets/no_quiet_spectrogram.png){ loading=lazy }  |                               |

### Mel Filterbanks

![](assets/mel_filter_banks.png){ loading=lazy }