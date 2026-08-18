package dev.isac.govflow.request.application.port.out;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface ServiceRequestRepository {
  ServiceRequest save(ServiceRequest serviceRequest);
}