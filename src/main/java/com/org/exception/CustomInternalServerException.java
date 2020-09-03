package com.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Custom exception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new Custom exception.
   *
   * @param message the message
   */
  public CustomInternalServerException(String message) {
    super(message);
  }

}
