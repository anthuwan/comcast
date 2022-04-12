/*
 * InstaRem Overseas Money Transfer.
 * https://www.instarem.com/en-in/
 *
 * Copyright (c) 2014-2019 InstaReM
 *
 * InstaRem is an acronym of Instant Remittance.
 * InstaRem Software is designed and developed to ease the Overseas Money Transfer.
 * It allows you to transfer your money overseas with minimum cost and time.
 *
 *
 * This file is licensed and cannot be accessed by outside InstaRem.
 * It can only be accessed and modified by the authorized InstaRem Technical Teams.
 * If any unauthorized, outside of the InstaRem, user found to be unlawfully modified
 * the content of this file,  will be prosecuted under the Copyright Act
 */

package com.comcast.stringinator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import com.comcast.stringinator.exception.GlobalExceptionHandler;
import com.comcast.stringinator.model.ErrorResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class GlobalExceptionHandlerTest {

  @InjectMocks
  ErrorResponse errorResponse;
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testHandleConversionFailedException()  {
    ConversionFailedException exception = mock(ConversionFailedException.class);
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    ResponseEntity<Object> response = controllerAdvice.handleConversionFailedException(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

  @Test
  public void testHandleIllegalArgumentException() {
    IllegalArgumentException exception = mock(IllegalArgumentException.class);
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    ResponseEntity<Object> response = controllerAdvice.handleIllegalArgumentException(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));

  }
  @Test
  public void testHandleMessageNotRead() {
    HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    ResponseEntity<Object> response = controllerAdvice.handleMessageNotRead(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

  @Test
  public void testHandleMethodNotSupported() {
    HttpRequestMethodNotSupportedException exception = mock(HttpRequestMethodNotSupportedException.class);
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    ResponseEntity<Object> response = controllerAdvice.handleMethodNotSupported(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.METHOD_NOT_ALLOWED));
  }

  @Test
  public void testHandleServletRequest() {
    ServletRequestBindingException exception = mock(ServletRequestBindingException.class);
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    ResponseEntity<Object> response = controllerAdvice.handleServletRequest(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

  @Test
  public void testHandleMethodArgumentNotValidException() throws Exception {
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "objectName");
    bindingResult.addError(new FieldError("objectName", "field1", "message"));
    bindingResult.addError(new FieldError("objectName", "field2", "message"));
    Method method = this.getClass().getMethod("testHandleMethodArgumentNotValidException", (Class<?>[]) null);
    MethodParameter parameter = new MethodParameter(method, -1);
    MethodArgumentNotValidException exception =
        new MethodArgumentNotValidException(parameter, bindingResult);
    ResponseEntity<Object> response = controllerAdvice.handleMethodArgumentNotValidException(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }
  @Test
  public void testhandleConstraintViolationException()  {
    GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
    final Set<ConstraintViolation<Object>> constraintViolations = new HashSet<>();

    ConstraintViolationException exception =
        new ConstraintViolationException(constraintViolations);
    ResponseEntity<Object> response = controllerAdvice.handleConstraintViolationException(exception);
    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

    @Test
    public void testHandleBuildError() {
      GlobalExceptionHandler controllerAdvice = new GlobalExceptionHandler();
      ServletRequestBindingException exception = mock(ServletRequestBindingException.class);
      ErrorResponse response = controllerAdvice.buildErrorResponse(exception.getMessage(),
          String.valueOf(HttpStatus.BAD_REQUEST.value()));
      assertNotNull(response);
    }

}
