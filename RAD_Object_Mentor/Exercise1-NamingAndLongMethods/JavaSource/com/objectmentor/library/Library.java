package com.objectmentor.library;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.models.Receipt;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.data.DataServices;

public class Library {
  private Catalog c;
  private DataServices ds;
  private Date tmpDate = null;

  public Library(DataServices ds) {
    c = new Catalog(ds);
    this.ds = ds;
  }

  public Book acceptBook(String isbn) {
    return c.add(isbn);
  }

  /**
   * 
   * @param string1 is the id of the copy (not the ISBN)
   * @param string2 is the id of the Patron borrowing the book
   * @return book
   */
  public Receipt borrow(String string1, String string2) {
    //find the copy using the find method that uses the ID
    Book c2 = c.find3(string1);
    
    //find the patron using the patron id
    Patron p = ds.findPatron(string2);
    
    //make a receipt
    Receipt r = new Receipt(p);

    //20040314 rcm SPR#12443432
    //20050519 jwg SPR#77452233
    //20050520 dac SPR#78552342
    //if there is a copy of the book, set the status to no such book
    if (c2 == null)
      r.setStatus(Receipt.NO_SUCH_BOOK);
    //otherwise...
    else {
      c2.setBorrowed(r);
      r.setStatus(Receipt.OK);
      r.setCopy(c2);
      Date d = (tmpDate == null) ? new Date() : tmpDate;
      Calendar c = Calendar.getInstance();
      c.setTime(d);
      c.add(Calendar.DAY_OF_YEAR, 14);
      Date d2 = c.getTime();
      r.setDate(d2);
    }
    return r;
  }

  public void setTmpDate(Date d) {
    tmpDate = d;
  }


}
