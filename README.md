# GovFlow

GovFlow is a guided learning project for practicing modern software architecture with Java, Spring Boot, microservices, APIs, messaging, observability, Kubernetes, and a React/Next.js frontend.

## Stack

- Java 21
- Spring Boot 4
- PostgreSQL 17
- Flyway
- JPA
- Kafka
- RabbitMQ
- REST
- gRPC
- GraphQL
- Docker
- Kubernetes
- Next.js 16
- React
- Tailwind CSS
- Storybook
- OpenTelemetry
- Prometheus
- Grafana
- ELK

## Domain

A public-service request management platform where citizens submit service requests and internal teams manage status, SLA, audit trails, and notifications.

## Structure

```
govflow/
  apps/
    web-admin/              # Next.js 16 + React + Tailwind + shadcn + Storybook
  services/
    request-service/        # Java 21 + Spring Boot + REST + JPA + Flyway
    notification-service/   # Java 21 + RabbitMQ consumer
    audit-service/          # Java 21 + Kafka consumer
    query-gateway/          # GraphQL + gRPC clients
  infra/
    docker-compose.yml
    k8s/
    observability/
      otel-collector/
      prometheus/
      grafana/
      elk/
  docs/
    adr/
    architecture/
```
