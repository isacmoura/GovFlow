package dev.isac.govflow.request.adapter.in.web;

record FieldErrorResponse(
    String field,
    String message
) {
}
