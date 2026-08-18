package dev.isac.govflow.request.application.port.in;

import java.util.UUID;

import dev.isac.govflow.request.domain.model.ServiceRequestStatus;

public record UpdateServiceRequestStatusCommand(
    UUID id,
    ServiceRequestStatus status
) {
}
