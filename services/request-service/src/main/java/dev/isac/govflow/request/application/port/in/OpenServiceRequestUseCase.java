package dev.isac.govflow.request.application.port.in;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface OpenServiceRequestUseCase {
  ServiceRequest open(OpenServiceRequestCommand command);
}