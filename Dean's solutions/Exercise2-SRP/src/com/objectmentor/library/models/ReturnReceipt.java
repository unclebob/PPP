package com.objectmentor.library.models;

import com.objectmentor.library.Money;

public class ReturnReceipt {
	public static final int UNKNOWN_BOOK = 1;
	public static final int UNBORROWED_BOOK = 2;
	public static final int OK = 0;
	public static final int LATE = 3;

	private Money fine = new Money(0);
	private BookCopy copy;
	private int status;
	public ReturnReceipt() {
		super();
	}
	
	public Money getFine() {
	    return fine;
	  }

	public void setFine(Money fine) {
	    this.fine = fine;
	  }

	public BookCopy getCopy() {
	    return copy;
	  }

	public void setCopy(BookCopy copy) {
	    this.copy = copy;
	  }

	public int getStatus() {
	    return status;
	  }

	public void setStatus(int status) {
	    this.status = status;
	  }

}
