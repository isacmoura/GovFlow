package dev.isac.govflow.request.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import dev.isac.govflow.request.application.port.out.ServiceRequestRepository;
import dev.isac.govflow.request.domain.model.ServiceRequest;

@Repository
class JpaServiceRequestPersistenceAdapter implements ServiceRequestRepository {
  private final SpringDataServiceRequestRepository repository;

  JpaServiceRequestPersistenceAdapter(
    SpringDataServiceRequestRepository repository
  ) {
    this.repository = repository;
  }

  @Override
  public ServiceRequest save(ServiceRequest serviceRequest) {
    ServiceRequestEntity entity = ServiceRequestPersistenceMapper.toEntity(serviceRequest);

    ServiceRequestEntity savedEntity = repository.save(entity);

    return ServiceRequestPersistenceMapper.toDomain(savedEntity);
  }
}
