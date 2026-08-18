package dev.isac.govflow.request.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import dev.isac.govflow.request.application.port.in.OpenServiceRequestCommand;
import dev.isac.govflow.request.application.port.in.OpenServiceRequestUseCase;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;
import dev.isac.govflow.request.domain.model.ServiceRequestStatus;
import dev.isac.govflow.request.support.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenServiceRequestIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private OpenServiceRequestUseCase openServiceRequestUseCase;

  @Autowired
  private ServiceRequestRepository serviceRequestRepository;

  @Test
  void shouldPersistOpenedServiceRequest() {
    // given
    OpenServiceRequestCommand command = new OpenServiceRequestCommand(
        "Maria Silva",
        "12345678900",
        "Street light is not working"
    );

    // when
    ServiceRequest serviceRequest = openServiceRequestUseCase.open(command);

    // then
    Optional<ServiceRequest> persistedServiceRequest = serviceRequestRepository.findById(
        serviceRequest.getId()
    );

    assertTrue(persistedServiceRequest.isPresent());
    assertEquals(serviceRequest.getId(), persistedServiceRequest.get().getId());
    assertEquals(serviceRequest.getProtocol(), persistedServiceRequest.get().getProtocol());
    assertEquals(ServiceRequestStatus.OPEN, persistedServiceRequest.get().getStatus());
  }
}
