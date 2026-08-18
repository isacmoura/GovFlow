package dev.isac.govflow.request.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record OpenServiceRequestHttpRequest(
  @NotBlank
  @Size(max = 120)
  String requesterName,

  @NotBlank
  @Size(max = 32)
  String requesterDocument,

  @NotBlank
  String description
) {
}