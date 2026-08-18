package dev.isac.govflow.request.adapter.in.web;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.isac.govflow.request.application.usecase.ServiceRequestNotFoundException;

@RestControllerAdvice
class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException exception
  ) {
    List<FieldErrorResponse> fields = exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> new FieldErrorResponse(
            fieldError.getField(),
            fieldError.getDefaultMessage()
        ))
        .sorted(Comparator.comparing(FieldErrorResponse::field))
        .toList();

    ErrorResponse response = ErrorResponse.withFields(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "Request validation failed",
        fields
    );

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  ResponseEntity<ErrorResponse> handleMessageNotReadableException(
      HttpMessageNotReadableException exception
  ) {
    ErrorResponse response = ErrorResponse.of(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "Malformed request body"
    );

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ErrorResponse> handleIllegalArgumentException(
      IllegalArgumentException exception
  ) {
    ErrorResponse response = ErrorResponse.of(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        exception.getMessage()
    );

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(IllegalStateException.class)
  ResponseEntity<ErrorResponse> handleIllegalStateException(
      IllegalStateException exception
  ) {
    ErrorResponse response = ErrorResponse.of(
        HttpStatus.UNPROCESSABLE_CONTENT.value(),
        HttpStatus.UNPROCESSABLE_CONTENT.getReasonPhrase(),
        exception.getMessage()
    );

    return ResponseEntity.unprocessableContent().body(response);
  }

  @ExceptionHandler(ServiceRequestNotFoundException.class)
  ResponseEntity<ErrorResponse> handleServiceRequestNotFoundException(
      ServiceRequestNotFoundException exception
  ) {
    ErrorResponse response = ErrorResponse.of(
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        exception.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
