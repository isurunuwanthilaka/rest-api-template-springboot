package com.company.demo.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

  USER_UNAVAILABLE("user is unavailable.");

  private String errorMessage;

  ErrorMessage(String err) {
    this.errorMessage = err;
  }

  public String toString() {
    return errorMessage;
  }
}
