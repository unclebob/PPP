package com.objectmentor.library.application.gateways;

public class IsbnDoesNotExistException extends RuntimeException {
  public IsbnDoesNotExistException(String message) { super(message); }

  public IsbnDoesNotExistException() { super(); }

  private static final long serialVersionUID = 6800857861178346517L;

}
