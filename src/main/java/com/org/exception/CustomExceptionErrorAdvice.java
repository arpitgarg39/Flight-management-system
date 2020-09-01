package com.org.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Custom exception error advice.
 */
@ControllerAdvice
public class CustomExceptionErrorAdvice {

  /**
   * Handle run time exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler({ RuntimeException.class })
  public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    return error(INTERNAL_SERVER_ERROR, e);
  }

  /**
   * Handle not found exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler({ CustomException.class })
  public ResponseEntity<String> handleNotFoundException(CustomException e) {
    return error(NOT_FOUND, e);
  }

  private ResponseEntity<String> error(HttpStatus status, Exception e) {
    return ResponseEntity.status(status).body(e.getMessage());
  }

}
