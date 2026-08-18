package dev.isac.govflow.request.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

  @Override
  public Optional<ServiceRequest> findById(UUID id) {
    return repository.findById(id)
        .map(ServiceRequestPersistenceMapper::toDomain);
  }

  @Override
  public List<ServiceRequest> findAll() {
    return repository.findAll()
        .stream()
        .map(ServiceRequestPersistenceMapper::toDomain)
        .toList();
  }
}
