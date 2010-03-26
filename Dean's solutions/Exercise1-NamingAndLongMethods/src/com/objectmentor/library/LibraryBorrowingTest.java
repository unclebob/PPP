package com.objectmentor.library;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import junit.framework.TestCase;

import java.util.Date;
import java.text.SimpleDateFormat;

public class LibraryBorrowingTest extends TestCase {
  private Library l;
  private Receipt r;
  private Patron p;
  private Book b;
  private MockDataServices ds;

  protected void setUp() throws Exception {
    ds = new MockDataServices();
    l = new Library(ds);
    p = new Patron("p");
    ds.addPatron(p);
    ds.setBookToReturn(new BookTitle("isbn"));
    b = l.acceptBook("isbn");
  }

  public void testCanBorrowBookThatLibraryHas() throws Exception {
    r = l.borrow(b.getId(), "p");
    assertEquals(Receipt.OK, r.getStatus());
    assertTrue(b.isBorrowed());
  }

  public void testCanNotBorrowBookLibraryDoesNotHave() throws Exception {
    r = l.borrow("isbnWeDontHave", "p");
    assertEquals(Receipt.NO_SUCH_BOOK, r.getStatus());
  }

  public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
    ds.setBookToReturn(new BookTitle("secondIsbn"));
    Book copy2 = l.acceptBook("secondIsbn");
    r = l.borrow(b.getId(), "p");
    assertEquals(Receipt.OK, r.getStatus());
    r = l.borrow(copy2.getId(), "p");
    assertEquals(Receipt.OK, r.getStatus());
  }

  public void testSetReturnDateTwoWeeksFromToday() throws Exception {
    Date now = new SimpleDateFormat("MM/dd/yyyy").parse("12/19/2006");
    Date returnDate = new SimpleDateFormat("MM/dd/yyyy").parse("1/2/2007");

    l.setTmpDate(now);
    r = l.borrow(b.getId(), "p");
    assertEquals(returnDate, r.getDate());
  }

  public void testReceiptRemembersBorrowingPatron() throws Exception {
    r = l.borrow(b.getId(), "p");
    assertEquals(p, r.getPatron());
  }

  public void testReceiptRemembersBorrowedCopy() throws Exception {
    r = l.borrow(b.getId(), "p");
    assertEquals("isbn", r.getCopy().getTitle().getIsbn());
  }
}
