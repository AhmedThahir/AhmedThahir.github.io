# Technical Analysis

1. Identify trend changes at early stage through predicting stock price patterns, using historical data (volume/price)
2. Identify when to buy/sell
3. Maintain investment position until evidence indicates that trend has reversed

## Notes

1. Probabilistic modelling; always uncertain, never deterministic
2. Continued success is dependent on keeping successful strategies known only to a few

## Premises

Market action determines everything

1. Everything will continue in state of rest/uniform motion unless compelled by external force
2. Market price is solely dependent on forces of demand and supply
3. Prices have a tendency to move in trends that persist for appreciable duration
4. Reversals of trends are caused by shifts in demand & supply
   1. There is a time gap b/w technicians perceiving a change and when investors assesses the change

## Tools

- Typical
  - Variables
    - Price
    - Volume
    - Rate of change
  - Charts & graphs
    - Line charts
      - Linear scale
      - Log scale
    - Bar charts: OHLC - Open High Low Close
    - Candle chart: 
    - Point & figure chart
- Dow Theory Measures
  - Moving averages
  - Momentum and oscillators
  - Breadth
- Market indicators
  - Investors’ sentiments
  - Contrary opinion
  - Professional investors’ behavior
  - Economic indicators

## Typical

|                   |                                                              |                                                              |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| OHLC              |                                                              | ![image-20240529170028277](assets/image-20240529170028277.png){ loading=lazy } |
| Candlestick chart | Candle color:<br />Red/black: close>open<br />Green/Blue: close>open | ![image-20240529170107135](assets/image-20240529170107135.png){ loading=lazy } |

## Dow Theory

Assumes that most shares follow the trend of the market most of the time

It intends to show the general trend/direction of the market as a whole and does not predict the direction of change in a particular security

In order to measure the “market”, two indices are used

- Industrial average: combination of blue-chip shares from industry
- Transportation average: shares of transport companies
  - To reinforce the conclusions obtained from Industrial Average

### Trends

|     | Primary                                                                                                            | Secondary                                                        | Tertiary|
|---       | ---                                                                                                                | ---                                                              | ---|
|          | Overall trend<br />- Business cycles<br />- Intrinsic value | Reactions that interrupt the progress of prices in primary trend<br /><br />May give wrong signals & confuse market | Random movements<br />Building blocks to secondary trends |
|Duration  | Few years                                                                                                          | Few months                                                       | Day-to-day |
|Direction | - bullish: upward<br/>- bearish: downward                                                                          | Opposing primary trend: Technical reaction<br />- Technical corrections: upward -> downward<br />- Technical rally: downward -> upward | |
| Concerns | Long-term investors | Weak holders<br />Traders | High-frequency traders |

### Principle of Confirmation

Whatever trends emerge in Industrial average must be confirmed by Transportation average

If trends in share prices are contradictory to industrial production/transportation of goods, then one should not design a trading strategy in shares and must wait until one gets confirmation of trends

### Price-Volume

Volume is the ‘fuel’ to move prices

Usually, $\text{Volume} \propto \text{Price}$ , ie volume

- contracts on decline
- expands on rallies/advances

If it is against this normal relationship, it is an indicator of an upcoming trend reversal. However, it should only be used as background information, since the actual reversals would be signaled by averages

### Price Actions

Price actions determine the trend

- Bullish indications: successive rallies penetrate peaks while the trough of an intervening decline is above the preceding trough
- Bearish indications: series of declining peaks and troughs

## Averages

[Time Series Filters](../../Economics/Econometrics/15_Time_Series_Filters.md) 

Usually uses the closing prices

Convention is to use 2 averages

- Slower average: larger window
- Faster average: smaller window

Notes

- Normally, an average moves along with a trend; but a reversal in trend may be captured by a crossover of 2 averages

- Signals to buy/sell are generated when the

  - price crosses the moving average

    or

  - one MA crosses the another

- Doubtful about this

  - Buy
    - price > moving average
    - Faster average > Slower average
  - Sell
    - price < moving average
    - Faster average < Slower average

- MA is a lagging indicator –> crossover will usually signal a trend reversal well after new trend has begin and is used mainly for confirmation

### Trend Channels

Trends have to be bounded

-  Trend channels
  - When prices trend between 2 parallel lines, this is referred to a channel
    - It is created by drawing 2 parallel lines
      - Line 1: Basic/main trendline
      - Line 2: Return/channel line

Use-case

- Represents area of support/resistance depending on direction of underlying trend
- Helps identify potential trend acceleration/reversal

|         |                                                              |
| ------- | ------------------------------------------------------------ |
| Bullish | ![image-20240529175113518](assets/image-20240529175113518.png){ loading=lazy } |
| Bearish | ![image-20240529175154194](assets/image-20240529175154194.png){ loading=lazy } |

### Envelops

2 symmetrical parallel lines to moving average

This is based on principle that prices fluctuate around a given trend in cyclical movements

Envelops consist of points of maximum and minimum divergence from some moving average

### Bollinger Bands

- Middle line: $\text{MA(close, $w$)}$
- Upper band: $\text{MA} + z_{\alpha/2} * \sigma (\text{close}, w)$
- Lower band: $\text{MA} - z_{\alpha/2} * \sigma (\text{close}, w)$

![image-20240529175645650](assets/image-20240529175645650.png){ loading=lazy }

Whenever bands narrow, a change in trend occurs: Whenever bands narrow, they have been stable for a while and it is followed by movement which is more volatile and in opposite direction

## Patterns

### Psychological barriers

Support & resistance levels

- Bargain hunters “support” the lower level upwards
- Profit takers “resist” the upper level downwards

- Breakout: prices go outside the support/resistance level
- Pullback: prices return within support/resistance level

![image-20240529200833863](assets/image-20240529200833863.png){ loading=lazy }

### Patterns

| Pattern                   | Trend     | Signal                  |                                                              |                                                              |
| ------------------------- | --------- | ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Head & Shoulders          | Uptrend   | Bearish<br />(reversal) | ![image-20240529200902458](assets/image-20240529200902458.png){ loading=lazy } | ![image-20240529201506077](assets/image-20240529201506077.png){ loading=lazy } |
| Inverted head & shoulders | Downtrend | Bullish<br />(reversal) | ![](assets/image-20240529201018721.png){ loading=lazy }                    | ![image-20240529201535589](assets/image-20240529201535589.png){ loading=lazy } |
| Symmetric triangle        | Uptrend   | Bullish                 | ![image-20240529201553435](assets/image-20240529201553435.png){ loading=lazy } | ![image-20240529201655006](assets/image-20240529201655006.png){ loading=lazy } |
| Symmetric triangle        | Downtrend | Bearish                 | ![image-20240529201602540](assets/image-20240529201602540.png){ loading=lazy } |                                                              |
| Ascending triangle        | Uptrend   | Bullish                 | ![image-20240529201821596](assets/image-20240529201821596.png){ loading=lazy } | ![image-20240529201848732](assets/image-20240529201848732.png){ loading=lazy } |
| Rectangle                 | Uptrend   | Bullish                 | ![image-20240529201951231](assets/image-20240529201951231.png){ loading=lazy } | ![image-20240529202040985](assets/image-20240529202040985.png){ loading=lazy } |
| Rectangle                 | Downtrend | Bearish                 | ![image-20240529202010595](assets/image-20240529202010595.png){ loading=lazy } | ![image-20240529202051177](assets/image-20240529202051177.png){ loading=lazy } |
| Flag                      | Uptrend   | Bullish                 | ![image-20240529202250442](assets/image-20240529202250442.png){ loading=lazy } |                                                              |
| Flag                      | Downtrend | Bearish                 | ![image-20240529202321410](assets/image-20240529202321410.png){ loading=lazy } |                                                              |
| Pennant                   | Uptrend   | Bullish                 | ![image-20240529202309127](assets/image-20240529202309127.png){ loading=lazy } |                                                              |
| Pennant                   | Downtrend | Bearish                 | ![image-20240529202339470](assets/image-20240529202339470.png){ loading=lazy } |                                                              |
| Cup & Handle              |           | Bullish                 | ![image-20240529202814226](assets/image-20240529202814226.png){ loading=lazy } |                                                              |
| Inverted Cup & Handle     |           | Bearish                 | ![image-20240529202905035](assets/image-20240529202905035.png){ loading=lazy } |                                                              |

## Momentum/Oscillator

Measures the velocity of price move

|                                                  | Indicates                                                    | Formula                                                      |                                                              |
| ------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| MACD<br />Moving Averages Convergence Divergence | Trend-Deviation                                              | $\dfrac{\text{Faster EMA}}{\text{Slower EMA}}$<br />or<br />$\text{Faster EMA} - \text{Slower EMA}$ |                                                              |
| Signal                                           | EMA of MACD                                                  |                                                              |                                                              |
| RSI<br />Relative Strength Index                 | Buying/selling ratio<br /><br />$\text{RSI} > 0.70 \implies$ Overbought<br />$\text{RSI} < 0.30 \implies$ Oversold | $\dfrac{\text{RS}}{1+\text{RS}}$<br />$\text{RS} = \dfrac{\text{Avg(gains)}_w}{\text{Avg(losses)}_w}$<br />where $w=$ window size | ![image-20240529204329480](assets/image-20240529204329480.png){ loading=lazy } |

