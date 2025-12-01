# Data Intensive Applications

Any of the following

 Any of the following generation/usage increases quickly:
- Volume of data
- Complexity of data
- Speed of change in data

## Pillars

| Pillar       | Properties                                                                                                                                                                                                                                                                                                                                                      |
| ------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Reliable     | Fault-tolerance<br>No authorized access<br>Chaos Testing<br>Robust to Full Machine Failures<br>Bug-free, Automated bug tests<br>Environments: Dev, Staging/Testing, Prod<br>Quick roll-backs                                                                                                                                                                    |
| Scalable     | Handle high traffic volume<br>Traffic load with peak # of reads, writes, simultaneous users<br>Capacity planning<br>Response time vs throughput<br>End user response time<br>90th, 95th percentile SLO/A service level objectives/agreements<br>Vertically-Scaling up (more powerful machine)<br>Horizontally-Scaling out (distributed across smaller machines) |
| Maintainable | Add new people to work<br>Productivity<br>Operable: Configurable and testable<br>Simple: easy to understand and ramp up, well-documented<br>Evolveable: easy to change                                                                                                                                                                                          |

## Components

![](assets/Data_Intensive_Applications_Typical_Components.png){ loading=lazy }


|                   |                                           | Tools                         |
| ----------------- | ----------------------------------------- | ----------------------------- |
| Databases         | Source of truth                           | SQL                           |
| Cache             | Temporary storage of expensive operation  | Memcache                      |
| Full-text index   | Quickly searching data by keyword/filter  | ESIndex<br>Apache Lucener     |
| Message queues    | MEssaging passing passing between process | Apache Kafka                  |
| Stream Processing |                                           | Apache Spark<br>Apache Samza  |
| Batch Processing  | Crunching last amount of data             | Apache Spark<br>Apache Hadoop |
| Application code  | Connective tissue other components        |                               |

# Databases

Data Model
- Relational model
- Document-based model
	- Not great for analytics
- Graph model

Aspects to keep in mind
- Data storage
- Data retrieval

ID

u
K

|               | OLTP                                   | OLAP                                  |
| ------------- | -------------------------------------- | ------------------------------------- |
|               | Online Transaction Processing Database | Online Analytical Processing Database |
|               | Row-oriented                           | Column-oriented                       |
| Optimized for | Writes                                 | Reads                                 |
| Flexibility   | High                                   | Low                                   |
|               | ![](./assets/oltp.png){ loading=lazy }                 | ![](./assets/olap.png){ loading=lazy }                |

## Structure

- Shallow dimension tables
- Dense fact tables

## Pipeline

- ETL
- ELT

## Code Compatibility

- Backward compatibility: Newer code can read data written by older code for old and new clients
- Forward Compatibility: Older code can read data written by newer code for old and new clients

## Rolling Upgrades

### Canary

```mermaid
flowchart TB
ut[User Traffic] -->
lb[Load Balancer]

lb --> mu[Most Users] --> cv1[Code Version 1]
lb --> fu[Few Users] --> cv2[Code Version 2]
```

## Replication

- Machine failures
- Latency for global audience
- Scale to large userbase
- Offline/network failures


Types
- Leader-Replica
	- Writes go to leader
	- Reads may/may not go to leader
	- Reads go to replica
- Multileader-Replica
- Leaderless
	- Write to all replicas
	- Read from all replicas
	- Eg: Amazon Dynamo, Voldemort, Cassandra

Aspects to consider
- Synchronous vs Asynchronous replication
- Replication lag
- Topology
- Durability vs availability vs latency
- Leader Failover
- Conflict resolution between leaders

## Database Partitioning

Sharding/Splitting/Horizontal Scaling
