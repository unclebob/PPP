package com.objectmentor.library.models;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.libraryRules.Damageable;
import com.objectmentor.library.libraryRules.Money;
import com.objectmentor.library.libraryRules.TimeSource;

public class Media implements Damageable {
  public static final int BOOK = 0;
  public static final int COMPACT_DISC = 1;

	public static final int SOFT_COVER = 0;
	public static final int HARD_COVER = 1;
	
	private int format;

	private final String isbn;

	private final String title;

	private final String author;

	private int ageRestriction;

	private int typeCode;
	
	public Media(String isbn, String title, String author, int typeCode) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.typeCode = typeCode;
	}

	public int howManyDaysThisCanBeBorrowed() {
		return typeCode == BOOK ? 14 : 7;
	}

	public Money getDamageCharge() {
		int cents = typeCode == BOOK ? 500 : 1500;
		return new Money(cents);
	}

	public Money perDiemFine() {
		int cents = typeCode == BOOK ? 50 : 150;
		return new Money(cents);
	}

	public String getId() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getTypeCode() {
		return typeCode;
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
    if (getTypeCode() != that.getTypeCode())
    	return false;
    if (!(getTitle().equals(that.getTitle())))
      return false;
    if (!(this.getAuthor().equals(that.getAuthor())))
      return false;
    return true;
  }
  
  public String toString() {
  	StringBuffer buff = new StringBuffer();
  	buff.append(super.toString())
  		.append(typeCode == BOOK ? "Book:" : "Compact Disc:")
  		.append(" title: \"")
  		.append(getTitle() != null ? getTitle() : "<unknown>")
  		.append(", author/artist: \"")
  		.append(getAuthor() != null ? getAuthor() : "<unknown>")
  		.append("\", how many days can it be borrowed? \"")
  		.append(howManyDaysThisCanBeBorrowed())
  		.append("\"");
  	return buff.toString();
  }
}
