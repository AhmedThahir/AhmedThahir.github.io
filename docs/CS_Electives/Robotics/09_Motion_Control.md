# Motion Control

PID (Proportional Integral Derivative) controllers are used to control movement of mobile robot.

Linear velocity loop controls the robot wheels speeds using motor speed feedback signal from the encoder

Angular velocity control loop keeps robot always in the accepted angle boundary using a 6 degree-of-freedom gyroscope and accelerometer as a feedback signal

```mermaid
flowchart LR
start(( )) -->
|"Desired Path<br/>x_d, y_d, &theta;_d"| ec[Error<br/>Calculation] 

ec -->|ex| cta
ec -->|ey| cta
ec -->|"e&theta;"| cta
cta[Conversion to Angular]

cta -->
|ed| fpidc[1st PID Controller] -->
|u_right| mrs

cta -->
|"e&theta;"| spidc[2nd PID Controller] -->
|u_left| mrs

mrs[Mobile Robot System] -->
|"Actual Path<br/>x_a, y_a, &theta;_a"| ec
```

