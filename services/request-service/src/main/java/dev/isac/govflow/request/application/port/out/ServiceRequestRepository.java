package dev.isac.govflow.request.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface ServiceRequestRepository {
  ServiceRequest save(ServiceRequest serviceRequest);

  Optional<ServiceRequest> findById(UUID id);

  List<ServiceRequest> findAll();
}
