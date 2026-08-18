package dev.isac.govflow.request.application.port.in;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface UpdateServiceRequestStatusUseCase {
  ServiceRequest updateStatus(UpdateServiceRequestStatusCommand command);
}
