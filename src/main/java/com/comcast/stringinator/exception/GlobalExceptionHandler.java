package com.comcast.stringinator.exception;

import com.comcast.stringinator.model.ErrorResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleServletRequest(ServletRequestBindingException ex) {
    log.error("inside  handleServletRequest", ex);
    ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(),
        String.valueOf(HttpStatus.BAD_REQUEST.value()));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ResponseEntity<Object> handleMethodNotSupported(
      HttpRequestMethodNotSupportedException ex) {
    log.warn("Returning HTTP 405 Bad Request", ex);
    ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(),
        String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleConversionFailedException(ConversionFailedException ex) {
    log.error("inside  handleConversionFailedException", ex);
    ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(),
        String.valueOf(HttpStatus.BAD_REQUEST.value()));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
    log.error("inside  handleIllegalArgumentException", ex);
    ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(),
        String.valueOf(HttpStatus.BAD_REQUEST.value()));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException exception) {
    ErrorResponse errorResponse = buildErrorResponse(exception.getMessage(),
        String.valueOf(HttpStatus.BAD_REQUEST.value()));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  public ErrorResponse buildErrorResponse(String errorMessage, String value) {
    ErrorResponse errorResponse =  new ErrorResponse();
    errorResponse.setStatusCode((value));
    errorResponse.setStatusDescription(errorMessage);
    return errorResponse;
  }
}
