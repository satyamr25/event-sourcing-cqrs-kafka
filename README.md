# Event Sourcing + CQRS using Kafka

## Overview
This project demonstrates **Event Sourcing with CQRS** using **Spring Boot**, **Apache Kafka**, and **Docker**.

Instead of persisting state directly in a database, the system stores **immutable domain events** in Kafka.
Read models are built **asynchronously** by consuming these events.

This project is designed to showcase **microservices**, **event-driven architecture**, and **containerization**.

---

## Architecture

### Microservices
- **Command Service**
    - Exposes REST APIs to create/update orders
    - Validates requests
    - Publishes domain events to Kafka

- **Query Service**
    - Consumes Kafka events
    - Builds read-optimized views
    - Exposes REST APIs for querying data

- **Audit Service**
    - Consumes Kafka events
    - Stores immutable event logs
    - Used for traceability and auditing

---

## Communication Patterns
- **REST APIs**
    - Synchronous communication (Feign Client / REST)
    - Used for validation and direct queries

- **Kafka**
    - Asynchronous, event-driven communication
    - Used for event sourcing and CQRS projections

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Kafka
- Apache Kafka & Zookeeper
- Spring Data JPA
- H2 Database (In-memory)
- Docker & Docker Compose

---

## Project Structure
```
event-sourcing-cqrs-kafka
│
├── command-service
│   ├── controller
│   ├── service
│   ├── config
│   └── CommandServiceApplication.java
│
├── query-service
│   ├── controller
│   ├── consumer
│   ├── repository
│   └── QueryServiceApplication.java
│
├── audit-service
│   ├── consumer
│   ├── repository
│   └── AuditServiceApplication.java
│
├── common-events
│   └── OrderEvent.java
│
├── docker-compose.yml
└── README.md
```

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
  "orderId": "ORD-1001",
  "amount": 1500.0,
  "createdAt": "2026-02-02T21:04:59.945365Z"
}
```

---

### Audit Service
**Get Audit Logs**
```bash
docker logs audit-service
```

---

## Kafka Topics
- `order-events` – Stores all order-related domain events

---

## Key Concepts Demonstrated
- Event Sourcing
- CQRS (Command Query Responsibility Segregation)
- Microservices Architecture
- Kafka-based Event Streaming
- Dockerized Services

---

## Notes
- Services are loosely coupled
- Shared `common-events` module is used only for **event contracts**
- No business logic is shared between services

---

## Author
Satyam Raj
