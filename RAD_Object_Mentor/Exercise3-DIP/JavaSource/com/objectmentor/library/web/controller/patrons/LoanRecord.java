package com.objectmentor.library.web.controller.patrons;

import java.util.Date;

import com.objectmentor.library.libraryRules.Money;

public class LoanRecord {
  public String id;
  public String title;
  public Date dueDate;
  public Money fine;
}
