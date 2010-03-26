package com.objectmentor.library;

import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockDataServices;
import com.objectmentor.library.models.Receipt;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.BookTitle;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.data.IsbnDoesNotExistException;

public class CatalogTest extends TestCase {

  public void testCatalog() {
    MockDataServices ds;
    Catalog c;
    ds = new MockDataServices();
    c = new Catalog(ds);
    //empty tests
    assertEquals("expected empty catalog", 0, c.getCount());
    assertFalse(c.exists("book"));
    assertNull(c.find2("book"));
    //one book test
		ds.setBookToReturn(new BookTitle("ISBN"));
    Book b1 = c.add("ISBN");
    assertEquals("ISBN", ds.wasLastCalledWithThisIsbn);
		assertEquals(ds.added.getTitle(), b1.getTitle());
    assertNull(c.find1("NOT ISBN"));
    assertNotNull(c.find1("ISBN"));
    assertTrue(c.exists("ISBN"));
    assertFalse(c.exists("NOT ISBN"));
    assertNotNull(c.find2("ISBN"));
		//multiple books
    ds.setBookToReturn(new BookTitle("ISBN 2"));
		Book b2 = c.add("ISBN 2");
		assertSame(b1, c.find1("ISBN"));
		assertSame(b2, c.find1("ISBN 2"));
    //borrow one of one
		List copies = c.findList("ISBN 2");
		Book bb = (Book) copies.get(0);
		bb.setBorrowed(new Receipt(new Patron("borrower")));
		assertEquals(null, c.find2("ISBN 2"));
    //non-existant
		try {
			c.add("NON-EXISTENT ISBN");
			fail();
		} catch (IsbnDoesNotExistException e) {
		}
		//multiple copies
    Book b1_2 = c.add("ISBN");
		List cl = c.findList("ISBN");
		assertEquals(2, cl.size());
		assertTrue(cl.contains(b1));
		assertTrue(cl.contains(b1_2));
    //borrow one of many
		bb = (Book) cl.get(0);
		bb.setBorrowed(new Receipt(new Patron("borrower")));
		assertNotNull(c.find2("ISBN"));
	}
}
