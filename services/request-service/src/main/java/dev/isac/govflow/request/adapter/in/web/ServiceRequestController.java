package dev.isac.govflow.request.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.isac.govflow.request.application.port.in.OpenServiceRequestCommand;
import dev.isac.govflow.request.application.port.in.OpenServiceRequestUseCase;
import dev.isac.govflow.request.domain.model.ServiceRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/service-requests")
class ServiceRequestController {
  private final OpenServiceRequestUseCase openServiceRequestUseCase;

  ServiceRequestController(OpenServiceRequestUseCase openServiceRequestUseCase) {
    this.openServiceRequestUseCase = openServiceRequestUseCase;
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
}
