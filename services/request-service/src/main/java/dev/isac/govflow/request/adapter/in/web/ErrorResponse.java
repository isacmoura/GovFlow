package dev.isac.govflow.request.adapter.in.web;

import java.time.OffsetDateTime;
import java.util.List;

record ErrorResponse(
    OffsetDateTime timestamp,
    int status,
    String error,
    String message,
    List<FieldErrorResponse> fields
) {
  static ErrorResponse of(
      int status,
      String error,
      String message
  ) {
    return new ErrorResponse(
        OffsetDateTime.now(),
        status,
        error,
        message,
        List.of()
    );
  }

  static ErrorResponse withFields(
      int status,
      String error,
      String message,
      List<FieldErrorResponse> fields
  ) {
    return new ErrorResponse(
        OffsetDateTime.now(),
        status,
        error,
        message,
        fields
    );
  }
}
