package dev.isac.govflow.request.adapter.in.web;

import java.time.OffsetDateTime;
import java.util.UUID;

import dev.isac.govflow.request.domain.model.ServiceRequest;
import dev.isac.govflow.request.domain.model.ServiceRequestStatus;

record ServiceRequestHttpResponse(
    UUID id,
    String protocol,
    String requesterName,
    String requesterDocument,
    String description,
    ServiceRequestStatus status,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt) {

      static ServiceRequestHttpResponse fromDomain(ServiceRequest serviceRequest) {
        return new ServiceRequestHttpResponse(
          serviceRequest.getId(),
          serviceRequest.getProtocol(),
          serviceRequest.getRequesterName(),
          serviceRequest.getRequesterDocument(),
          serviceRequest.getDescription(),
          serviceRequest.getStatus(),
          serviceRequest.getCreatedAt(),
          serviceRequest.getUpdatedAt()
        );
      }
}
