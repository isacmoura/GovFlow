package dev.isac.govflow.request.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class ServiceRequest {
  private final UUID id;
  private final String protocol;
  private final String requesterName;
  private final String requesterDocument;
  private final String description;
  private ServiceRequestStatus status;
  private final OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  private ServiceRequest(
      UUID id,
      String protocol,
      String requesterName,
      String requesterDocument,
      String description,
      ServiceRequestStatus status,
      OffsetDateTime createdAt,
      OffsetDateTime updatedAt) {
    this.id = Objects.requireNonNull(id);
    this.protocol = requireText(protocol, "protocol");
    this.requesterName = requireText(requesterName, "requesterName");
    this.requesterDocument = requireText(requesterDocument, "requesterDocument");
    this.description = requireText(description, "description");
    this.status = Objects.requireNonNull(status);
    this.createdAt = Objects.requireNonNull(createdAt);
    this.updatedAt = Objects.requireNonNull(updatedAt);
  }

  public static ServiceRequest open(
      String protocol,
      String requesterName,
      String requesterDocument,
      String description) {
    OffsetDateTime now = OffsetDateTime.now();

    return new ServiceRequest(
        UUID.randomUUID(),
        protocol,
        requesterName,
        requesterDocument,
        description,
        ServiceRequestStatus.OPEN,
        now,
        now);
  }

  public static ServiceRequest restore(
      UUID id,
      String protocol,
      String requesterName,
      String requesterDocument,
      String description,
      ServiceRequestStatus status,
      OffsetDateTime createdAt,
      OffsetDateTime updatedAt) {
    return new ServiceRequest(
        id,
        protocol,
        requesterName,
        requesterDocument,
        description,
        status,
        createdAt,
        updatedAt);
  }

  public void startProgress() {
    if (status != ServiceRequestStatus.OPEN) {
      throw new IllegalArgumentException("Only open requests can be started");
    }

    status = ServiceRequestStatus.IN_PROGRESS;
    updatedAt = OffsetDateTime.now();
  }

  public void resolve() {
    if (status != ServiceRequestStatus.IN_PROGRESS) {
      throw new IllegalStateException("Only in-progress requests can be resolved");
    }

    status = ServiceRequestStatus.RESOLVED;
    updatedAt = OffsetDateTime.now();
  }

  public void cancel() {
    if (status == ServiceRequestStatus.RESOLVED) {
      throw new IllegalStateException("Resolved requests cannot be cancelled");
    }

    status = ServiceRequestStatus.CANCELLED;
    updatedAt = OffsetDateTime.now();
  }

  private static String requireText(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(fieldName + " must not be blank");
    }

    return value.trim();
  }

  public UUID getId() {
    return id;
  }

  public String getProtocol() {
    return protocol;
  }

  public String getRequesterName() {
    return requesterName;
  }

  public String getRequesterDocument() {
    return requesterDocument;
  }

  public String getDescription() {
    return description;
  }

  public ServiceRequestStatus getStatus() {
    return status;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }
}
