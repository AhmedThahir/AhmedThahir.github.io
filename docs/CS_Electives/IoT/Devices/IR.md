# Infrared Sensor

Working principle: Reflection of light

## Uses

- Obstacle detection
- Differentiate between colors 

## Components

- Transmitter: IR LED
- Receiver: Photodiode (Reverse LED)
- Comparator IC for voltage comparison
- Potentiometer to set sensitivity of sensor, by controlling voltage threshold for comparator
  - $V_s \ne \{0, V_{cc} \}$

## Working steps

1. Transmitter emits IR rays
2. Light gets reflected by obstacle
3. Receiver gets the reflected light
4. Received light converted into voltage

## Limitations

- Cannot obtain position of obstacle; only for Object-Detection (binary)
- Obstacle detection only works for light-colored obstacle
  - dark colored objects will absorb light

## Circuit Diagram

![ir_sensor_circuit_diagram](./assets/ir_sensor_circuit_diagram.png){ loading=lazy }

## Code

```cpp
int objected_detected

void setup(){
  pinMode(D1, INPUT);		// sensor
  pinMode(D2, OUTPUT);	// output device (LED)
  
  Serial.begin(9600);
}
void loop(){
  objected_detected = digitalRead(D1);
  
  Serial.println(objected_detected);
  
	if (objected_detected == 1) {
    digitalWrite(D2, HIGH);
  } else {
    digitalWrite(D2, LOW);
  }
  
  delay(500);
}
```

