package com.objectmentor.library.models;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.libraryRules.TimeSource;

public class Media {
  public static final int BOOK = 0;
  public static final int COMPACT_DISC = 1;

  private String id;
  private String title;
  private int ageRestriction;
  private final int typeCode;
  private String author;

  /**
   * Use this constructor for a Book
   */
  protected Media(String id, String title, String author) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.typeCode = BOOK;
  }

  /**
   * Use this constructor for Periodicals or CDS.
   *
   * @param title
   * @param idOrIssue (id for CD, issue for Periodical)
   * @param typeCode
   */
  protected Media(String title, String idOrIssue, int typeCode) {
    this.typeCode = typeCode;
    this.id = idOrIssue;
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public boolean canLoan(Patron borrower) {
    if (ageRestriction == 0)
      return true;

    Calendar allowedAfter = determineAllowedAfterDate(borrower);

    Calendar today = determineTodaysDate();

    return allowedAfter.compareTo(today) < 0;
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

  public String getAuthor() {
    if (typeCode == BOOK)
      return author;
    else
      throw new IllegalAccessError();
  }


  public int getTypeCode() {
    return typeCode;
  }
}
