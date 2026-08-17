# ADR 0001: Architecture Style

## Status

Accepted

## Context

GovFlow will be built as a modular, distributed system to practice microservices, REST APIs, event-driven communication, observability, and frontend integration.

## Decision

We will use a hexagonal architecture style for backend services.

Each service should separate:

- Domain model
- Application use cases
- Inbound ports
- Outbound ports
- Inbound adapters
- Outbound adapters
- Infrastructure configuration

Services will communicate through REST, events, gRPC, and GraphQL depending on the use case.

## Consequences

This structure adds some initial complexity, but improves testability, separation of concerns, and long-term maintainability.
