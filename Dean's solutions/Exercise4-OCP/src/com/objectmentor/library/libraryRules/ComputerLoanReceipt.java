package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.*;

public class ComputerLoanReceipt {

  public static final String OK = "Ok";
  public static final String NO_SUCH_COMPUTER = "noComputer";
  public static final String NO_SUCH_PATRON = "noPatron";
  public static final String COMPUTER_NOT_AVAILABLE = "notAvailable";

  private String status;

  public ComputerLoanReceipt(Computer computer, Patron patron) {
    if (computer == null)
      status = NO_SUCH_COMPUTER;
    else if (patron == null)
      status = NO_SUCH_PATRON;
    else
      status = OK;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;

  }

}
