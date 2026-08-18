package dev.isac.govflow.request.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import dev.isac.govflow.request.application.port.in.OpenServiceRequestCommand;
import dev.isac.govflow.request.application.port.out.ProtocolGenerator;
import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;
import dev.isac.govflow.request.domain.model.ServiceRequestStatus;

class OpenServiceRequestServiceTest {

  private static class FixedProtocolGenerator implements ProtocolGenerator {

    private final String protocol;

    private FixedProtocolGenerator(String protocol) {
      this.protocol = protocol;
    }

    @Override
    public String nextProtocol() {
      return protocol;
    }
  }

  private static class InMemoryServiceRequestRepository implements ServiceRequestRepository {

    private final Map<UUID, ServiceRequest> serviceRequests = new HashMap<>();

    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
      serviceRequests.put(serviceRequest.getId(), serviceRequest);
      return serviceRequest;
    }

    @Override
    public Optional<ServiceRequest> findById(UUID id) {
      return Optional.ofNullable(serviceRequests.get(id));
    }

    @Override
    public List<ServiceRequest> findAll() {
      return serviceRequests.values()
          .stream()
          .toList();
    }
  }

  @Test
  void shouldOpenServiceRequest() {
    // given
    ServiceRequestRepository repository = new InMemoryServiceRequestRepository();
    ProtocolGenerator protocolGenerator = new FixedProtocolGenerator("REQ-2026-000001");
    OpenServiceRequestService service = new OpenServiceRequestService(repository, protocolGenerator);

    OpenServiceRequestCommand command = new OpenServiceRequestCommand(
        "Maria Silva",
        "12345678900",
        "Street light is not working");

    // when
    ServiceRequest serviceRequest = service.open(command);

    // then
    assertNotNull(serviceRequest.getId());
    assertEquals("REQ-2026-000001", serviceRequest.getProtocol());
    assertEquals("Maria Silva", serviceRequest.getRequesterName());
    assertEquals("12345678900", serviceRequest.getRequesterDocument());
    assertEquals("Street light is not working", serviceRequest.getDescription());
    assertEquals(ServiceRequestStatus.OPEN, serviceRequest.getStatus());
  }
}
