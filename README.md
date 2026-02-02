# Event Sourcing + CQRS using Kafka

## Overview
This project demonstrates a **CQRS (Command Query Responsibility Segregation)** and **Event Sourcing** based microservices architecture using **Apache Kafka, Spring Boot, Docker, and Feign Client**.

The system is designed to show:

- Asynchronous, event-driven communication using Kafka
- Synchronous inter-service communication using REST (Feign Client)
- Clean separation of write and read responsibilities (CQRS)
- Containerized microservices orchestrated via Docker Compose

---

## Architecture

### Microservices
- **Command Service (Write Side)**
    - Accepts write requests (commands) via REST
    - Validates input and creates domain events
    - Publishes events to Kafka
    - Acts as the source of business rules
    - Exposes a read-only internal REST API for capability checks (used by Feign)

- **Query Service (Read Side)**
    - Consumes Kafka events
    - Builds read-optimized projections
    - Exposes GET APIs for querying data
    - Uses Feign Client to synchronously call the Command Service for read-side enrichment 
    - Does not publish events or modify state

- **Audit Service**
    - Consumes Kafka events
    - Stores immutable event logs
    - Used for traceability and auditing

---

## Communication Patterns
- **Kafka (Asynchronous)**
  #### Used for **core business** communication:
    ```
    Command Service → Kafka → Query Service
                              Audit Service
    ```
    - Decoupled 
    - Event-driven 
    - Eventually consistent 
    - Replayable event history


- **Feign (Synchronous)**
  #### Used for read-side enrichment only::
    ```
    Query Service → Feign → Command Service
    ```
    - Read-only internal API 
    - No state mutation 
    - No CQRS violation 
    - Demonstrates REST-based microservice communication

---

## CQRS Design Rationale
| Rule                        | How it is enforced                       |
| --------------------------- | ---------------------------------------- |
| Commands change state       | Only Command Service handles writes      |
| Queries never change state  | Query Service is read-only               |
| No sync calls in write flow | Writes use Kafka only                    |
| Loose coupling              | Services communicate via events          |
| REST without CQRS violation | Feign used only for read-side enrichment |


---

## Tech Stack
- Java 17
- Spring Boot
- Spring Kafka
- Apache Kafka & Zookeeper
- Feign
- Spring Data JPA
- H2 Database (In-memory)
- Docker & Docker Compose

---

## Project Structure
```
event-sourcing-cqrs-kafka
│
├── command-service
│   ├── config
│   ├── controller
│   ├── dto
│   ├── internal
│   ├── service
│   └── CommandServiceApplication.java
│
├── query-service
│   ├── client
│   ├── config
│   ├── consumer
│   ├── controller
│   ├── model
│   ├── repository
│   └── QueryServiceApplication.java
│
├── audit-service
│   ├── consumer
│   └── AuditServiceApplication.java
│
├── common-events
│   └── OrderEvent.java
│
├── docker-compose.yml
└── README.md
```

---

## End-to-End Flow
**Write Flow**

1. Client sends POST /orders 
2. Command Service publishes OrderCreatedEvent to Kafka 
3. Query Service consumes event and updates read DB 
4. Audit Service consumes event and stores audit log

**Read Flow**

1. Client calls GET /orders/{orderId} 
2. Query Service reads data from read DB 
3. Query Service calls Command Service via Feign for capabilities 
4. Combined response is returned to client

---

## How to Run (Docker)

### Prerequisites
- Docker
- Docker Compose

### Start All Services
```bash
docker-compose up --build
```

### Stop All Services
```bash
docker-compose down
```

---

## API Testing

### Command Service
**Create Order**
```bash
curl --location 'http://localhost:8081/orders' \
--header 'Content-Type: application/json' \
--data '{
  "orderId": "ORD-1001",
  "amount": 1500
}'
```
Response:
- `202 Accepted`
- Event is published to Kafka

---

### Query Service
**Get Order**
```bash
curl --location 'http://localhost:8082/orders/ORD-1001'
```
Response:
- `200 OK`
```json  
{
  "order": {
    "orderId": "ORD-1001",
    "amount": 1500.0,
    "createdAt": "2026-02-02T22:33:26.826262Z"
  },
  "capabilities": {
    "canCancel": true,
    "canModify": false,
    "status": "ACTIVE"
  }
}
```

---

### Audit Service
**Get Audit Logs**
```bash
docker logs audit-service
```

---

## Notes
- Services are loosely coupled
- Shared `common-events` module is used only for **event contracts**
- No business logic is shared between services

---

## Author
Satyam Raj
