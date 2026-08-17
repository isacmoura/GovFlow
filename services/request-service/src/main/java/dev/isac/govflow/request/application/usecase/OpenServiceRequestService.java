package dev.isac.govflow.request.application.usecase;

import org.springframework.transaction.annotation.Transactional;

import dev.isac.govflow.request.application.port.in.OpenServiceRequestCommand;
import dev.isac.govflow.request.application.port.in.OpenServiceRequestUseCase;
import dev.isac.govflow.request.application.port.out.ProtocolGenerator;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;

public class OpenServiceRequestService implements OpenServiceRequestUseCase {
  private final ServiceRequestRepository serviceRequestRepository;
  private final ProtocolGenerator protocolGenerator;

  public OpenServiceRequestService(
    ServiceRequestRepository serviceRequestRepository,
    ProtocolGenerator protocolGenerator
  ) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.protocolGenerator = protocolGenerator;
  }

  @Override
  @Transactional
  public ServiceRequest open(OpenServiceRequestCommand command) {
    String protocol = protocolGenerator.nextProtocol();

    ServiceRequest serviceRequest = ServiceRequest.open(
      protocol,
      command.requesterName(),
      command.requesterDocument(),
      command.description()
    );

    return serviceRequestRepository.save(serviceRequest);
  }
}
