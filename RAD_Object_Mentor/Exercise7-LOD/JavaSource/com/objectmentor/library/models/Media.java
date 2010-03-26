package com.objectmentor.library.models;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.libraryRules.Damageable;
import com.objectmentor.library.libraryRules.Money;
import com.objectmentor.library.libraryRules.TimeSource;

public abstract class Media implements Damageable {
  protected String id;
  private String title;
  private int ageRestriction;
  private String author;

  public Media(String id, String title, String author) {
    this.id = id;
    this.title = title;
    this.author = author;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
  
  public String getAuthor() {
    return author;
  }

  public abstract int howManyDaysThisCanBeBorrowed();

  public abstract Money perDiemFine();

  public boolean canLoan(Patron borrower) {
    if (ageRestriction == 0)
      return true;

    Calendar allowedAfter = determineAllowedAfterDate(borrower);

    Calendar today = determineTodaysDate();

    return allowedAfter.getTime().getTime() < today.getTime().getTime();
  }

  private Calendar determineTodaysDate() {
    Calendar today = Calendar.getInstance();
    Date now = TimeSource.time();
    today.setTime(now);
    return today;
  }

  private Calendar determineAllowedAfterDate(Patron borrower) {
    Date birthDay = borrower.getBirthDate();
    Calendar allowedAfter = Calendar.getInstance();
    allowedAfter.setTime(birthDay);
    allowedAfter.add(Calendar.YEAR, ageRestriction);
    return allowedAfter;
  }

  public void setAgeRestriction(int age) {
    this.ageRestriction = age;
  }
  
  public String toString() {
  	StringBuffer buff = new StringBuffer();
  	buff.append("id: \"")
  		.append(getId())
  		.append("\", title: \"")
  		.append(getTitle())
  		.append("\", age restriction \"")
  		.append(ageRestriction)
  		.append("\"");
  	return buff.toString();
  }

}
