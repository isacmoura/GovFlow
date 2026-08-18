package dev.isac.govflow.request.adapter.in.web;

import dev.isac.govflow.request.domain.model.ServiceRequestStatus;
import jakarta.validation.constraints.NotNull;

record UpdateServiceRequestStatusHttpRequest(
    @NotNull
    ServiceRequestStatus status
) {
}
