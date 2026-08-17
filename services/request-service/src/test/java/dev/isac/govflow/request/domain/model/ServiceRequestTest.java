package dev.isac.govflow.request.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ServiceRequestTest {

  private ServiceRequest openDefaultRequest() {
    return ServiceRequest.open(
        "REQ-2026-000002",
        "Maria Silva",
        "12345678901",
        "Street light is not working");
  }

  @Test
  void shouldOpenServiceRequest() {
    // given
    String protocol = "REQ-2026-000001";
    String requesterName = "John Doe";
    String requesterDocument = "12345678900";
    String description = "Street light is not working";

    // when
    ServiceRequest request = ServiceRequest.open(
        protocol,
        requesterName,
        requesterDocument,
        description);

    // then
    assertNotNull(request.getId());
    assertEquals(protocol, request.getProtocol());
    assertEquals(requesterName, request.getRequesterName());
    assertEquals(requesterDocument, request.getRequesterDocument());
    assertEquals(description, request.getDescription());
    assertEquals(ServiceRequestStatus.OPEN, request.getStatus());
    assertNotNull(request.getCreatedAt());
    assertNotNull(request.getUpdatedAt());
  }

  @Test
  void shouldStartProgressWhenRequestIsOpen() {
    // given
    ServiceRequest request = openDefaultRequest();

    // when
    request.startProgress();

    // then
    assertEquals(ServiceRequestStatus.IN_PROGRESS, request.getStatus());
  }

  @Test
  void shouldResolveWhenRequestIsInProgress() {
    // given
    ServiceRequest request = openDefaultRequest();
    request.startProgress();

    // when
    request.resolve();

    // then
    assertEquals(ServiceRequestStatus.RESOLVED, request.getStatus());
  }

  @Test
  void shouldNotResolveOpenRequest() {
    // given
    ServiceRequest request = openDefaultRequest();

    // when
    IllegalStateException exception = assertThrows(
        IllegalStateException.class,
        request::resolve);

    // then
    assertEquals("Only in-progress requests can be resolved", exception.getMessage());
  }

  @Test
  void shouldNotCancelResolvedRequest() {
    // given
    ServiceRequest request = openDefaultRequest();
    request.startProgress();
    request.resolve();

    // when
    IllegalStateException exception = assertThrows(
        IllegalStateException.class,
        request::cancel);

    // then
    assertEquals("Resolved requests cannot be cancelled", exception.getMessage());
  }

  @Test
  void shouldRejectBlankRequiredFields() {
    // given
    String blankRequesterName = "";

    // when
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> ServiceRequest.open(
            "REQ-2026-000001",
            blankRequesterName,
            "12345678900",
            "Street light is not working"));

    // then
    assertEquals("requesterName must not be blank", exception.getMessage());
  }
}