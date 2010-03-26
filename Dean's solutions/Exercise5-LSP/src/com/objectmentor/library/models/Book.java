package com.objectmentor.library.models;

import com.objectmentor.library.libraryRules.Money;

public class Book extends Media {
	public static final int SOFT_COVER = 0;
	public static final int HARD_COVER = 1;

	private int format;

	public Book(String isbn, String title, String author) {
		super(isbn, title, author);
	}

	public int howManyDaysThisCanBeBorrowed() {
		return 14;
	}

	public Money getDamageCharge() {
		return new Money(500);
	}

	public Money perDiemFine() {
		return new Money(50);
	}

	public boolean usesFormat(int format) {
		return this.format == format;
	}

	public void setFormat(int format) {
		this.format = format;
	}
}
