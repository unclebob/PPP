package com.objectmentor.library.models;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.libraryRules.Damageable;
import com.objectmentor.library.libraryRules.Money;
import com.objectmentor.library.libraryRules.TimeSource;

public abstract class Media implements Damageable {

	protected final String id;
	protected final String title;
	protected final String author;
	private int ageRestriction;

	public Media(String id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public abstract int howManyDaysThisCanBeBorrowed();

	public abstract Money getDamageCharge();

	public abstract Money perDiemFine();

	public String getId() {
		return id;
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