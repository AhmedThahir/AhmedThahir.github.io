Task of moving information b/w computers over the network is divided into smaller and more manageable problems.

Each problem is considered as a different layer in the network, which reduces complexity.

Each layer

- provides service to layer above & belo
- communicates with the same layer’s software or hardware on other computer

There are 2 network standards

## ISO OSI Standard

International Organization of Standardization-Open System Interconnection

The upper 3 layers of the OSI model (application, presentation and session—Layers 7, 6 and 5) are orientated more toward services to the applications

Lower 4 layers (transport, network, data link and physical —Layers 4, 3, 2, and 1) are concerned with the flow of data from end to end through the network.

![image-20230404174342590](./assets/image-20230404174342590.png){ loading=lazy }

| Type     | Layer        | Description                                                  | [PDU](#PDU)              | Device/Example                         | Address                                | Delivery                                    | Protocols                                                    | Transmission<br />Mode                    | Line<br />Configuration       | Service<br />Type                              |
| -------- | ------------ | ------------------------------------------------------------ | ------------------------ | -------------------------------------- | -------------------------------------- | ------------------------------------------- | ------------------------------------------------------------ | ----------------------------------------- | ----------------------------- | ---------------------------------------------- |
| Logical  | Application  | Provides  network-access services to user                    | Data/Page                | Whatsapp<br />Browser<br />Mail client |                                        |                                             | HTTP<br />FTP<br />SMTP<br />SNMP<br />DNS<br />NFS<br />Telnet<br />DHCP |                                           |                               |                                                |
|          | Presentation | Data/File format<br />Data Translation<br />Protocol conversion<br />Syntax & Semantics<br />Compression/Decompression<br />Encryption/Decryption | Data/Page                |                                        |                                        |                                             | SSL<br />TLS                                                 |                                           |                               |                                                |
|          | Session      | Session creation, maintainence, termination<br />Dialogue control & synchronization b/w 2 end systems<br />Token Management<br />Password Validation<br />Logical connection request<br />Synchronization & checkpointing of pages | Data/Page                |                                        |                                        |                                             | PPTP<br />SIP<br />SAP<br />Net<br />BIOS                    | Half-duplex<br />Full-duplex              |                               |                                                |
|          | Transport    | Ensuring reliable data exchange mechanism<br />Error control (only end-systems: source-dest)<br />Flow control<br />Connection control<br />Service point addressing<br />Segmentation/Re-assembly into/from a packet | Segment                  |                                        | Port<br />(identifies process/service) | Process-to-Process                          | TCP<br />UDP                                                 | Multiplex                                 |                               | Connectionless<br />Connection-oriented        |
| Hardware | Network      | Inter-Networking<br />Routing algo<br />IP addressing<br />Congestion handling<br />Packetizing<br />Fragmenting | Packet/<br />Datagram    | Router                                 | IP                                     | Host-to-Host                                | IPv4, IPv6<br />IPSec<br />ICMP<br />IGP<br />EGP<br />OGHP<br />RARP<br />ARP |                                           |                               |                                                |
|          | Data Link    | Ensuring reliable communication over physical layer<br />‘Framing’/Reassembling<br />Error control (router & end-system: source-dest + each hop)<br />Error correction/handling<br />Corruption detection/correction<br />Flow control (pacing b/w adjacent sending & receiving nodes)<br />Access control<br />LAN formation<br />Physical addressing & matching | Frame                    | Bridges<br />Switches                  | MAC                                    | [Hop-to-Hop Delivery](#Hop-to-Hop-Delivery) | ATM<br />SLIP<br />Frame<br />Relay<br />PPP                 | Simplex<br />Half-Duplex<br />Full-Duplex | Point-to-Point<br />Broadcast |                                                |
|          | Physical     | Convert signal b/w digital & analog<br />Encryption & decryption<br />Representation of bits<br />Data rate<br />Synchronization of bits<br />Encoding<br />Modulation<br />Line Configuration<br />Transmission medium<br />Transmission mode<br />Topology | Bitstream/<br />Raw Data | Hub<br />Repeater                      |                                        |                                             | USB<br />Bluetooth                                           |                                           |                               | Connection-Oriented<br />(most reliable layer) |

### PDU

Protocol data unit

PDU’s are used for peer-to-peer contact between corresponding layers

### Packet

| H3<br />(Header)                              | Data |
| --------------------------------------------- | ---- |
| Source IP address<br />Destination IP address |      |

### Frame

| H2<br />(Header of layer 2)                                  | Data | T2<br />(Trailer of layer 2) |
| ------------------------------------------------------------ | ---- | ---------------------------- |
| Source MAC Address<br />Destination MAC Address<br />(found through Hop-to-Hop Delivery) |      | Usually a parity             |

## Analogy

*12 kids in Ann’s house sending letters to 12 kids in Bill’s house:*

- hosts = houses
- processes = kids
- app messages = letters in envelopes

transport protocol = Ann’ multiplexing and Bill’ demultiplexing to in-house siblings

network-layer protocol = postal service

## TCP/IP

Transmission Control Protocol with inter-networking protocol

- Application
- Transport
- Network
- Data Link
- Physical

## OSI vs TCP/IP

|                 | OSI                                 | TCP/IP                              |
| --------------- | ----------------------------------- | ----------------------------------- |
| No of Layers    | 7                                   | 5                                   |
| Transport Layer | Connection-oriented/Connection-less | Connection-oriented/Connection-less |
| Network layer   | Connection-oriented                 | Connection-less                     |
| Delivery model  | ‘Best’                              | ‘Best-effort’                       |

## Addresses

| Address                                                      |               Size (in Bits)               |  Denotion   |            Example             |              Separator              | Connect device<br />in ___ network |         Set during         |                            Fixed                             | Administered by                                              |                    Portable                     |
| ------------------------------------------------------------ | :----------------------------------------: | :---------: | :----------------------------: | :---------------------------------: | :--------------------------------: | :------------------------: | :----------------------------------------------------------: | ------------------------------------------------------------ | :---------------------------------------------: |
| Specific                                                     |                                            |             |                                |                                     |                                    |                            |                                                              |                                                              |                                                 |
| Port                                                         |                     16                     |   Decimal   | 753<br />(0-1024 are reserved) |      (none; it is a single no)      |                                    |                            |                                                              |                                                              |                                                 |
| IP/Logical/Host                                              |                     32                     |   Decimal   |          192.168.22.5          |                 Dot                 |             different              | Connection<br />to network |                              ❌                               |                                                              | ❌<br />(address depends on connected IP subnet) |
| MAC(Medium Access Control)/<br />Ethernet/<br />LAN/<br />Physical/<br />Link | 48<br />24 Vendor Code,<br />24 Serial No) | Hexadecimal |       AA.F0.C1.E2.77.51        | Colon (Linux)<br />Hyphen (Windows) |                same                |  Device<br />manufacture   | ✅<br />(usually burnt into NIC ROM;<br />sometimes software-configurable) | IEEE<br />(Manufacturer buys portion of MAC address space for uniqueness) |         ✅<br />(LAN card can be moved)          |

- MAC address is like Social Security Number
- IP address is like postal address

### idk

The physical addresses will change from hop to hop, but the logical and port addresses usually remain the same. Huh???

### IPv4

| Class | Byte 1 (Decimal) | Byte 1 (Binary) |
| ----- | ---------------- | --------------- |
| A     | 0-127            | 0…              |
| B     | 128-191          | 10…             |
| C     | 192-223          | 110…            |
| D     | 224-299          | 1110…           |
| E     | 240-255          | 1111…           |

Network ID is the first IP address, for eg: `10.0.0.0, 20.0.0.0`. This is used to refer to all devices in a network.

Only end-devices and routers require IP address, as they belong to network layer.

## Protocols

| Layer                                  | Protocol | Full Form                            | Details                                                      |
| -------------------------------------- | -------- | ------------------------------------ | ------------------------------------------------------------ |
| Network                                | IP       | Internet Protocol                    |                                                              |
| Network                                | ICMP     | Internet Control Message Protocol    | `ping` command uses this                                     |
| Network                                | IGMP     | Internet Group Message Protocol      |                                                              |
| Network +<br />Data Link<br />(Hybrid) | ARP      | Address resolution protocol          | Convert ip address to mac address                            |
| Network +<br />Data Link<br />(Hybrid) | RARP     | Reversed Address resolution protocol | Convert mac address to ip address<br />(Only required when connecting to a network for the first time) |
