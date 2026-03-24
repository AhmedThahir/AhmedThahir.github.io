# Introduction

## Applications

- Path Planning
- Motion Control
- Computer Vision
- Machine Learning
- Optimization
- NLP
- Manipulator & Mobile Robot Hardware
- Computer Vision, Pathfinding and other tasks

## Agent

Anything that can be viewed as

- perceiving its environment through **sensors**
- acting upon that environment through **effectors**

|        | Sensors                                                   | Effectors       |
| ------ | --------------------------------------------------------- | --------------- |
| Humans | Eyes<br />Nose<br />Skin<br />Tongue                      | Hands<br />Legs |
| Robots | Cameras<br />Infrared Range Finders<br />Thermal Scanners | Motors          |

## Robot

Software-controllable device using sensors to guide effectors through programmed motion in a workspace to manipulate physical objects

## Types of Robots

- Mobile
- Stationary
- Autonomous
- Remote-Controlled
- Virtual

## Robot Control System

```mermaid
flowchart LR

e[/Environment/] -->
Sensors -->
|Send<br/>Telemetery| r[Radio] -->
|Decision| c[Controllers] --> Actuators

Operator <--> cs[Control<br/>Station] <-.->
|Data<br/>Link| r

subgraph Actuators
	direction LR
	Motors
	Servos
end
```

