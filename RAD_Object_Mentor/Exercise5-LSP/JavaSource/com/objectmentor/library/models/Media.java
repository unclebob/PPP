package com.objectmentor.library.models;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.libraryRules.Damageable;
import com.objectmentor.library.libraryRules.Money;
import com.objectmentor.library.libraryRules.TimeSource;

public abstract class Media implements Damageable {

	public static final int SOFT_COVER = 0;
	public static final int HARD_COVER = 1;
	private int format;
	protected final String isbn;
	protected final String title;
	protected final String author;
	private int ageRestriction;

	public Media(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public abstract int howManyDaysThisCanBeBorrowed();

	public abstract Money getDamageCharge();

	public abstract Money perDiemFine();

	public String getId() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAgeRestriction(int age) {
		this.ageRestriction = age;
	}

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

	public boolean usesFormat(int format) {
		return this.format == format;
	}

	public void setFormat(int format) {
		this.format = format;
	}
	
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (!(obj.getClass() == getClass())) return false;      
    Media that = (Media) obj;
    if (!(getTitle().equals(that.getTitle())))
      return false;
    if (!(this.getAuthor().equals(that.getAuthor())))
      return false;
    return true;
  }
  

}