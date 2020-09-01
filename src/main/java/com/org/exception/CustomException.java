package com.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Custom exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new Custom exception.
   *
   * @param message the message
   */
  public CustomException(String message) {
    super(message);
  }

}
