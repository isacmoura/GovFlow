package dev.isac.govflow.request.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.isac.govflow.request.application.port.in.ListServiceRequestsUseCase;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;

@Service
class ListServiceRequestsService implements ListServiceRequestsUseCase {
  private final ServiceRequestRepository serviceRequestRepository;

  ListServiceRequestsService(ServiceRequestRepository serviceRequestRepository) {
    this.serviceRequestRepository = serviceRequestRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<ServiceRequest> list() {
    return serviceRequestRepository.findAll();
  }
}
