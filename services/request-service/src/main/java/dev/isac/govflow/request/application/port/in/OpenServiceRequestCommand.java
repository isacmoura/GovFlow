package dev.isac.govflow.request.application.port.in;

public record OpenServiceRequestCommand(
  String requesterName,
  String requesterDocument,
  String description
){}