package dev.isac.govflow.request.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.isac.govflow.request.application.port.in.GetServiceRequestUseCase;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;

@Service
class GetServiceRequestService implements GetServiceRequestUseCase {
  private final ServiceRequestRepository serviceRequestRepository;

  GetServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
    this.serviceRequestRepository = serviceRequestRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public ServiceRequest getById(UUID id) {
    return serviceRequestRepository.findById(id)
        .orElseThrow(() -> new ServiceRequestNotFoundException(id));
  }
}
