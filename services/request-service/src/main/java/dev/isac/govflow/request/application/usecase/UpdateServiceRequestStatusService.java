package dev.isac.govflow.request.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.isac.govflow.request.application.port.in.UpdateServiceRequestStatusCommand;
import dev.isac.govflow.request.application.port.in.UpdateServiceRequestStatusUseCase;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;
import dev.isac.govflow.request.domain.model.ServiceRequestStatus;

@Service
class UpdateServiceRequestStatusService implements UpdateServiceRequestStatusUseCase {
  private final ServiceRequestRepository serviceRequestRepository;

  UpdateServiceRequestStatusService(ServiceRequestRepository serviceRequestRepository) {
    this.serviceRequestRepository = serviceRequestRepository;
  }

  @Override
  @Transactional
  public ServiceRequest updateStatus(UpdateServiceRequestStatusCommand command) {
    ServiceRequest serviceRequest = serviceRequestRepository.findById(command.id())
        .orElseThrow(() -> new ServiceRequestNotFoundException(command.id()));

    applyStatusTransition(serviceRequest, command.status());

    return serviceRequestRepository.save(serviceRequest);
  }

  private void applyStatusTransition(
      ServiceRequest serviceRequest,
      ServiceRequestStatus status
  ) {
    switch (status) {
      case IN_PROGRESS -> serviceRequest.startProgress();
      case RESOLVED -> serviceRequest.resolve();
      case CANCELLED -> serviceRequest.cancel();
      case OPEN -> throw new IllegalStateException("Requests cannot be moved back to OPEN");
    }
  }
}
