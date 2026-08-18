package dev.isac.govflow.request.application.port.in;

import java.util.List;

import dev.isac.govflow.request.domain.model.ServiceRequest;

public interface ListServiceRequestsUseCase {
  List<ServiceRequest> list();
}
