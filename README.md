# Event Sourcing + CQRS using Kafka

## Overview
This project demonstrates Event Sourcing with CQRS using Spring Boot, Kafka, and Docker.

Instead of persisting state directly, the system stores immutable domain events in Kafka.
Read models are built asynchronously by consuming events.

## Architecture
- Command Service: Accepts write requests and publishes events
- Query Service: Builds read-optimized views from Kafka events
- Audit Service: Stores immutable event logs for traceability

## Communication
- REST (Feign): Synchronous service validation
- Kafka: Asynchronous event-driven communication

## Tech Stack
- Java 17
- Spring Boot
- Apache Kafka
- Spring Data JPA
- Docker & Docker Compose

## How to Run
```bash
docker-compose up --build
