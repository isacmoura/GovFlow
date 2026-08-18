package dev.isac.govflow.request.adapter.in.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.isac.govflow.request.application.port.in.GetServiceRequestUseCase;
import dev.isac.govflow.request.application.port.in.ListServiceRequestsUseCase;
import dev.isac.govflow.request.application.port.in.OpenServiceRequestCommand;
import dev.isac.govflow.request.application.port.in.OpenServiceRequestUseCase;
import dev.isac.govflow.request.application.port.in.UpdateServiceRequestStatusCommand;
import dev.isac.govflow.request.application.port.in.UpdateServiceRequestStatusUseCase;
import dev.isac.govflow.request.domain.model.ServiceRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/service-requests")
class ServiceRequestController {
  private final OpenServiceRequestUseCase openServiceRequestUseCase;
  private final ListServiceRequestsUseCase listServiceRequestsUseCase;
  private final GetServiceRequestUseCase getServiceRequestUseCase;
  private final UpdateServiceRequestStatusUseCase updateServiceRequestStatusUseCase;

  ServiceRequestController(
      OpenServiceRequestUseCase openServiceRequestUseCase,
      ListServiceRequestsUseCase listServiceRequestsUseCase,
      GetServiceRequestUseCase getServiceRequestUseCase,
      UpdateServiceRequestStatusUseCase updateServiceRequestStatusUseCase
  ) {
    this.openServiceRequestUseCase = openServiceRequestUseCase;
    this.listServiceRequestsUseCase = listServiceRequestsUseCase;
    this.getServiceRequestUseCase = getServiceRequestUseCase;
    this.updateServiceRequestStatusUseCase = updateServiceRequestStatusUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ServiceRequestHttpResponse open(@Valid @RequestBody OpenServiceRequestHttpRequest request) {
    OpenServiceRequestCommand command = new OpenServiceRequestCommand(
      request.requesterName(),
      request.requesterDocument(),
      request.description()
    );

    ServiceRequest serviceRequest = openServiceRequestUseCase.open(command);

    return ServiceRequestHttpResponse.fromDomain(serviceRequest);
  }

  @GetMapping
  List<ServiceRequestHttpResponse> list() {
    return listServiceRequestsUseCase.list()
        .stream()
        .map(ServiceRequestHttpResponse::fromDomain)
        .toList();
  }

  @GetMapping("/{id}")
  ServiceRequestHttpResponse getById(@PathVariable UUID id) {
    ServiceRequest serviceRequest = getServiceRequestUseCase.getById(id);

    return ServiceRequestHttpResponse.fromDomain(serviceRequest);
  }

  @PatchMapping("/{id}/status")
  ServiceRequestHttpResponse updateStatus(
      @PathVariable UUID id,
      @Valid @RequestBody UpdateServiceRequestStatusHttpRequest request
  ) {
    UpdateServiceRequestStatusCommand command = new UpdateServiceRequestStatusCommand(
        id,
        request.status()
    );

    ServiceRequest serviceRequest = updateServiceRequestStatusUseCase.updateStatus(command);

    return ServiceRequestHttpResponse.fromDomain(serviceRequest);
  }
}
