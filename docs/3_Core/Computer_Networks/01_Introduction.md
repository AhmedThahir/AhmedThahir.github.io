# Introduction

## Data Communication

Exchange of data b/w devices via transmission medium, where data is information presented in form agreed by involved parties. Termed from ‘telecommunication’ - communication at a distance

### Components of Data Communication

- Message
- Sender
- Sending protocol
- Medium
- Receiver
- Receiving protocol

### Node

Device capable of sending/receiving data to/from other notes on network

## Network

Set of devices connected by communication links

### Purpose

Share resourcces

- File sharing
- Hardware sharing
- Application sharing: Client/server apps
- Network graming
- User Commuication
  - Voice over IP (VoIP): allows calls over traditional IP rather than by traditional PTSN

### Distance-Based Classification

|      | Range                        | Example      |
| ---- | ---------------------------- | ------------ |
| LAN  | Short                        | Wifi         |
| MAN  | Specific area (city, campus) | Cable TV     |
| WAN  | Long                         | The Internet |

### Parts of Network

| Part              | Role                                                         |
| ----------------- | ------------------------------------------------------------ |
| ‘The Internet’    |                                                              |
| Router            | Connect internet to ‘The Internet’<br />has intelligence<br />(represented using $\otimes$) |
| Firewall          | Rules to adhere on which messages to be allowed              |
| Switch            | Helps form a LAN (Local Area Network)<br />No of ports will always be $2^n$ |
| File Server       |                                                              |
| Database Server   |                                                              |
| File Server       |                                                              |
| WiFi Access Point | kinda like a wireless switch<br />connected to wired swetch  |

Links between one/more routers should be a ‘dedicated link’

## Transmission Modes & Media

| Type                            | Medium              | Range     | Requires                                   | Example                   |
| ------------------------------- | ------------------- | --------- | ------------------------------------------ | ------------------------- |
| Wired                           | Twisted Pair Cables | Short     |                                            | Landline, Ethernet cable  |
|                                 | Coaxial Cables      | Long      |                                            |                           |
|                                 | Fibreoptic Cables   | Very Long |                                            |                           |
| Wireless<br />(Frequency bands) | Radio Waves         | Long      | Omni-directional antenna                   | Car radio                 |
|                                 | Micro Waves         | Long      | Uni-directional Microwave antenna<br />Lon | Etisalat connection tower |
|                                 | Infrared Waves      | Short     |                                            | Bluetooth                 |

Wireless can
- Infrastructure-Based: Mobile Network
- Infrastructure-less: Bluetooth

## IDK

### Transmission Modes

|             | Direction | Order                                                | Example       |
| ----------- | :-------: | ---------------------------------------------------- | ------------- |
| Simplex     |    Uni    |                                                      | Car Radio     |
| Half-Duplex |    Bi     | Sequential<br />(one direction after the other)      | Walkie-Talkie |
| Full-Duplex |    Bi     | Simultaneous<br />(both directions at the same time) | Telephone     |

### Line Configurations

|                | Example                                            |
| -------------- | -------------------------------------------------- |
| Point-to-Point | Connection from ISP to home router                 |
| Multi-Point    | Multiple devices connected to a single home router |

### Topology

Arrangement of nodes in a network

|              | Bus                                               | Ring                                                                                    | Star                                                            | Mesh                                                                 | Hybrid|
|---           | ---                                               | ---                                                                                     | ---                                                             | ---                                                                  | ---|
|Arrangement   | Sequential                                        | Each node connected to 2 adjacent nodes                             | Nodes directly connected to a central ‘controller’ | Every device connected to every other device<br />in point-point manner | Combination of star and bus|
|Working | Devices collectively help transfer data b/w points<br />Terminators stop signals after reaching end of wire,<br />to prevent signal bounce | Token-Passing<br />(Token: Message which gives priority to a station to use ring)<br />- Data hops from one device to another until it reaches its destination<br/>- Each device communicates its routing info to every other connected device<br/>- Each device then determines either passes/keep received data |  |  | |
|Device Used   | Tap<br />Drop line                                | [Repeater](#Repeater)                                                                   | Hub/Switch/Router |                                                                      | |
|Advantage     | Simple<br />Cheap<br />Easy installation<br />Node failure does not affect others | Easier to manage<br />Easier to locate defective node/cable problem<br />Great for transmitting signals over long distances on a LAN<br />Handles high-volume network traffic<br />Enables reliable communication | - Good for modern networks<br />- Low startup costs<br />- Easy to manage<br />- Easy to expand<br />- Great availability of equipment<br />- Scalable<br />- High security | Highest redundancy<br />Low failure chance<br />Low traffic<br />Easy fault identification<br />Robust | |
|Disadvantage  | Not fault-tolerant<br />Prone to congestion<br />No security | Expensive<br />Single point of failure<br />Requires more cable & network equipment at start<br />Fewer equipment options<br />Fewer options for high-speed upgrades<br />Only one station can send message<br />Requires tokens<br />Requires multiple repeaters<br />No security | Single point of failure - If hub fails, everything fails<br />Possible congestion at hub<br />Requires more cables than bus | Expensive<br />(Many cables, I/O port, connections) | Same as star |
|Method | Half-Duplex | Simplex | Duplex? |  | |
|Example       | [Ethernet](#Ethernet)                             |                                                                                         |                                                                 | Between ISP routers                                                  | |
|Duplex/Half-Duplex links  | 1 | 0 | $n$ | $\frac{n(n-1)}{2}$                                                  | |
|Simplex links | 0 | 1 | $2n$ | $n(n-1)$                                                          | |
|Diagram | ![image-20230403084200131](./assets/image-20230403084200131.png){ loading=lazy } | ![image-20230403084507147](./assets/image-20230403084507147.png){ loading=lazy } | ![image-20230403102605664](./assets/image-20230403102605664.png){ loading=lazy } | ![image-20230403103256383](./assets/image-20230403103256383.png){ loading=lazy } | ![image-20230403103435171](./assets/image-20230403103435171.png){ loading=lazy } |

## Network Devices

|                  |                                                              |
| ---------------- | ------------------------------------------------------------ |
| End Points       | PCs, Servers, Printers, etc                                  |
| Interconnections | Media, Connectors<br />NIC(Network Interface Card)/LAN Card/Ethernet Card) |
| Bridge           | (not used anymore)                                           |
| Switches         | Connects endpoints to LAN<br />Multi-Port Bridge             |
| Router           | Connect multiple LANs to form internetworks<br />Chooses best path between LAN & WAN |
| Repeater         | Repeats [Token](#Token) in a round-robin fashion<br />Helps overcome signal attenuation |
| Hub              | Device without any intelligence<br/>Multi-port repeater<br/>Not used much anymore<br/>It will just broadcast every packet, as it cannot select devices. |

![Network symbols | vijayababuj](./assets/network_devices.jpg){ loading=lazy }

## Network Rules

### Protocol

Consists rules for the following aspects

| Aspect    | Meaning                           |
| --------- | --------------------------------- |
| Syntax    | Format of data                    |
| Semantics | Meaning of each section of bits   |
| Timing    | Timing and speed of data transfer |


## The Internet

Network of networks, consisting of

- Connected computing devices
- communication links
- Routers
- Protoctols
- Communication infrasture for distributed applications
- Communication services

### Standard

Collection of [protocol](#protocol)s agreed by organizations, such as ITU, IEEE

| De Facto Standards        | De Jure Standards              |
| ------------------------- | ------------------------------ |
| Approved by organizations | Adopted through widespread use |

For eg

- Wired LAN uses standard `IEEE 802.3`
- WiFi (WirelessFidelity) uses standard `802.11`

#### Internet Standards

- Internet draft
- RFC (Request for Comment)

### Models

| Model         |                                        | Example                          |
| ------------- | -------------------------------------- | -------------------------------- |
| Client-Server | 1 Client<br />1 Server                 | WWW<br />Email                   |
| Peer-to-Peer  | End devices use each other’s resources | Torrenting<br />Teleconferencing |

## Types of Services

|                      | Connection-Oriented                                          | Connection-Less                                              |
| -------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Stages               | 1. Set up connection<br />2. Receive acknoledgement<br />3. Send data<br />4. Receive acknowlegment<br />5. Repeat steps 3-4 | Send data                                                    |
| Reliable             | ✅                                                            | ❌                                                            |
| Flow Control         | ✅                                                            | ❌                                                            |
| Congestion Control   | ✅                                                            | ❌                                                            |
| Speed                | Slower                                                       | Faster                                                       |
| Example Protocol     | TCP<br />(Transmission Control Protocol)                     | UDP<br />(User Datagram Protocol)                            |
| Example Applications | HTTP (WWW)<br />FTP (File Transfer Protocol)<br />Telnet (Remote Login<br />SMTP (Simple Mail Transfer Protocol) | Streaming media<br />Teleconferencing<br />Internet telephony |

## Switching/Routing Mechanism

|                                                   | Circuit Switching                                            | Packet Switching                                             |
| ------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Type                                              | Physical                                                     | Logical                                                      |
|                                                   | Dedicated circuit per call: telephone net                    | Data sent in discrete ‘chunks’<br />Each packet uses full link bandwidth |
| Steps                                             | - Establish physical connection<br/>- Network resources divided into pieces<br />- Pieces allocated to calls<br />- Data Transmission<br/>- Teardown | - Split data into packets<br />- Transmit packets one hope at a time<br />- Packet reaches receiver |
| Resource reservation                              | ✅                                                            | ❌                                                            |
| Resources allocated                               | Fixed                                                        | On-Demand (Dynamic)                                          |
| Advantages                                        |                                                              | Line efficiency (Single link can be shared by multiple packets)<br />Data rate conversion<br />Packets are accepted even when network is busy (delayed, but still accepted)<br />Priorities can be set |
| Disadvantage                                      | Resource piece idle if not used by owning call (no sharing)<br />Call setup required |                                                              |
| Connection Type                                   | Connection-oriented                                          | Connection-less (Virtual Circuit Approach)<br />Connection-oriented (Datagram Approach) |
| Total resource demand<br />can exceed  available? | ❌                                                            | ✅                                                            |
| Congestion<br />Control?                          | ❌                                                            | ✅                                                            |
| Performance<br />guaranteed?                      | ✅                                                            | ❌                                                            |

## Resource Division

1. Frequency division
2. Time division
3. Code division

### TDM

Time Division Multiplexing

![image-20230405104158746](./assets/image-20230405104158746.png){ loading=lazy }

## Subnet Mask

This is the value to perform `and` operation

To get the value, just make the network bits of the IP address as 1s and host bits as 0s

