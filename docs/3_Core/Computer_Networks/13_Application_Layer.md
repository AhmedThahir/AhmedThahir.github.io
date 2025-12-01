Applications are the entities that communicate with each other to exchange services

To make any use of the Internet, application programs should run on the two endpoints of a network connection

- “*Client*” applications request service
- “*Server*” applications provide service

A socket is one end of an inter-process communication channel.

## Client Server Model

Many-to-One

![image-20230531080409830](./assets/image-20230531080409830.png){ loading=lazy }

| Server                                                       | Client                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Run all the time (i.e. infinite)<br/>Provide service to any client<br/>Usually specialize in providing certain type of service, e.g. Mail<br/>Listen to a well-known port and passively open connection. | Run when needed, then terminate (i.e. finite)<br/>Actively open TCP or UDP connection with Server’s socket |

- Client needs to know existence & address of server 
- However, the server does not need to know the existence or address of the client prior to the connection
- Once a connection is established, both sides can send and receive information

### Connection Establishment

- Both client and server will construct a socket
- The process to establish a socket on the client side is different from the process to establish a socket on the server side

## P2P (Peer-to-Peer) Model

Every node in the network acts alike

![image-20230531080922328](./assets/image-20230531080922328.png){ loading=lazy }

### Advantages

- No central point of failure
- Scalability

- Since every peer is alike, it is possible to add more peers to the system and scale to larger networks

### Disadvantages

- Decentralized coordination; How to keep global state consistent?
- All nodes may not be equal

  - Computing power
  - Bandwidth

## HTTP

HyperText Transfer Protocol

- HTTP 1.0: RFC 1945
- HTTP 1.1: RFC 2068 (persistent TCP)

Used for Client-Server model

- Client: Browser request & receive Web objects
- Server: Web server sends objects in response to requests

### Properties

- Uses TCP
  - 
- “Stateless”
  - A ‘state’ is information kept in memory
    of a host, server or router to reflect
    past events: such as routing tables,
    data structures or database entries
  - HTTP server maintains no information about past client requests
  - Protocols that maintain “state” are complex!
    - history (state) is maintained
    - if server/client crashes, views of “state” may be inconsistent, must be reconciled
    - state is added via ‘cookies’

### Steps

![image-20230531081340853](./assets/image-20230531081340853.png){ loading=lazy }

### Types

|                                                | Non-Persistent HTTP                                          | Persistent HTTP without Pipelining                           | Persistent HTTP with Pipelining                              |
| ---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Max no of objects sent over TCP connection     | 1                                                            | Multiple                                                     | Multiple                                                     |
| Used in HTTP Version                           | HTTP/1.0                                                     |                                                              | HTTP/1.1                                                     |
| Categories                                     |                                                              |                                                              |                                                              |
| Steps                                          | ![image-20230531082217335](./assets/image-20230531082217335.png){ loading=lazy } | Server leaves connection open after sending response<br/>Subsequent HTTP messages  between same client/server sent over open connection<br />Client issues new request only when previous response has been received<br/>One RTT for each referenced object | Server leaves connection open after sending response<br/>Subsequent HTTP messages  between same client/server sent over open connection<br />Client sends requests as soon as it encounters a referenced object<br/>1 RTT for all referenced objects |
| Total Response Time<br />($n =$ no of objects) | $n (2 \ \text{RTT} + \text{Transmit Time})$<br />- one RTT to initiate TCP connection<br/>- one RTT for HTTP request & first few bytes of HTTP response to return<br/>- file transmission time | $n (1 \ \text{RTT} + \text{Transmit Time})$                  | $1 \ \text{RTT} + n(\text{Transmit Time})$                   |
| Disadvantages                                  | - requires 2 RTTs per object<br />- OS overhead for each TCP connection<br />- browsers often open parallel TCP connections to fetch referenced object | ![image-20230531083528283](./assets/image-20230531083528283.png){ loading=lazy } | ![image-20230531083555548](./assets/image-20230531083555548.png){ loading=lazy } |

| Non-Persistent & Non-Parallel                                | Non-Persistent & Parallel                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![image-20230531082411779](./assets/image-20230531082411779.png){ loading=lazy } | ![image-20230531082730947](./assets/image-20230531082730947.png){ loading=lazy } |

## HTTP Messages

|         | HTTP Request                                                 | HTTP Response                                                |
| ------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Format  | ![image-20230531083718069](./assets/image-20230531083718069.png){ loading=lazy } | ![image-20230531083738877](./assets/image-20230531083738877.png){ loading=lazy } |
| Example | ![image-20230531083903322](./assets/image-20230531083903322.png){ loading=lazy } | ![image-20230531083908719](./assets/image-20230531083908719.png){ loading=lazy } |

![image-20230531083826765](./assets/image-20230531083826765.png){ loading=lazy }

### Headers

![image-20230531084050547](./assets/image-20230531084050547.png){ loading=lazy }

### Responses

![image-20230531084022375](./assets/image-20230531084022375.png){ loading=lazy }

## HTTP Methods

![image-20230531084007375](./assets/image-20230531084007375.png){ loading=lazy }
