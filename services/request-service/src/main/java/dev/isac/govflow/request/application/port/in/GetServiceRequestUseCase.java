package dev.isac.govflow.request.application.port.in;

import java.util.UUID;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface GetServiceRequestUseCase {
  ServiceRequest getById(UUID id);
}
