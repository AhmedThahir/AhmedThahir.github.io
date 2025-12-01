# Ultrasonic

Technical name: HC-SR04

More precise than IR sensor

Working principle: Reflection of soundwaves

Ultrasonic waves travel faster than speed of audible sound

Range: 3cm to 4m

Accuracy: 3mm

## Components

- Emitter
  - Emitter sends out an Ultrasonic pulse at 40 kHz which travels through the air, and returns if it bounces back from an object.

- Receiver

## Measurement

Measures distance of object by emitting Ultrasonic sound waves, and converts reflected sound into electrical signal, and then by calculating the travel time & speed of sound, the distance can be calculated.
$$
D = S \times \dfrac{t}{2}
$$

## Pins

- VCC: +5v
- GND
- TRIG: Emitter (D3)
- ECHO: Receiver 

![image-20240222001729823](./assets/ultrasound_pin_diagram.png){ loading=lazy }

## Code

```cpp
int emit_duration = 10; // microseconds
long speed = 330; // m/s
long time;

void setup() {
  pinMode(2, OUTPUT); // trig
  pinMode(3, INPUT); // echo
  
  Serial.begin(9600);
}
void loop() {
  // emit ultrasonic waves for 10 microsec
  digitalWrite(2, HIGH);
  delayMicroseconds(emit_duration);
  
  digitalWrite(2, LOW);
  delayMicroseconds(emit_duration);
  
  time = pulseIn(3, HIGH); // microseconds
  time /= 1000 * 1000; // seconds
  
  distance = speed * (time/2);
 
  delay(5000);
}
```

