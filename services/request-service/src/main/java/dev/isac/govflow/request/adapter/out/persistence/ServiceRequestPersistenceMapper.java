package dev.isac.govflow.request.adapter.out.persistence;

import dev.isac.govflow.request.domain.model.ServiceRequest;

final class ServiceRequestPersistenceMapper {
  private ServiceRequestPersistenceMapper() {
  }

  static ServiceRequestEntity toEntity(ServiceRequest serviceRequest) {
    return new ServiceRequestEntity(
        serviceRequest.getId(),
        serviceRequest.getProtocol(),
        serviceRequest.getRequesterName(),
        serviceRequest.getRequesterDocument(),
        serviceRequest.getDescription(),
        serviceRequest.getStatus(),
        serviceRequest.getCreatedAt(),
        serviceRequest.getUpdatedAt());
  }

  static ServiceRequest toDomain(ServiceRequestEntity entity) {
    return ServiceRequest.restore(
      entity.getId(),
      entity.getProtocol(),
      entity.getRequesterName(),
      entity.getRequesterDocument(),
      entity.getDescription(), 
      entity.getStatus(), 
      entity.getCreatedAt(),
      entity.getUpdatedAt()
    );
  }
}