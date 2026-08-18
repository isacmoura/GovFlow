package dev.isac.govflow.request.adapter.out.persistence;

import java.time.OffsetDateTime;
import java.util.UUID;

import dev.isac.govflow.request.domain.model.ServiceRequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "service_requests")
class ServiceRequestEntity {
  @Id
  private UUID id;

  @Column(nullable = false, unique = true, length = 32)
  private String protocol;

  @Column(name = "requester_name", nullable = false, length = 120)
  private String requesterName;

  @Column(name = "requester_document", nullable = false, length = 32)
  private String requesterDocument;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 32)
  private ServiceRequestStatus status;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  protected ServiceRequestEntity() {
  }

  ServiceRequestEntity(
      UUID id,
      String protocol,
      String requesterName,
      String requesterDocument,
      String description,
      ServiceRequestStatus status,
      OffsetDateTime createdAt,
      OffsetDateTime updatedAt) {
    this.id = id;
    this.protocol = protocol;
    this.requesterName = requesterName;
    this.requesterDocument = requesterDocument;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  UUID getId() {
    return id;
  }

  String getProtocol() {
    return protocol;
  }

  String getRequesterName() {
    return requesterName;
  }

  String getRequesterDocument() {
    return requesterDocument;
  }

  String getDescription() {
    return description;
  }

  ServiceRequestStatus getStatus() {
    return status;
  }

  OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }
}
