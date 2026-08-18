package dev.isac.govflow.request.application.usecase;

import java.util.UUID;

public class ServiceRequestNotFoundException extends RuntimeException {

  public ServiceRequestNotFoundException(UUID id) {
    super("Service request not found: " + id);
  }
}
