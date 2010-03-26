package com.objectmentor.library.models;

import java.util.Date;

public class BorrowedReceipt {
	public static final int UNKNOWN_BOOK = 1;
	public static final int UNBORROWED_BOOK = 2;
	public static final int OK = 0;
	public static final int LATE = 3;

	private Patron patron;
	private Date returnDate;
	private BookCopy copy;
	private int status;

	public BorrowedReceipt(Patron patron) {
		this.patron = patron;
	}

	public Date getReturnDate() {
	    return returnDate;
	  }

	public void setReturnDate(Date returnDate) {
	    this.returnDate = returnDate;
	  }

	public Patron getBorrower() {
	    return patron;
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
