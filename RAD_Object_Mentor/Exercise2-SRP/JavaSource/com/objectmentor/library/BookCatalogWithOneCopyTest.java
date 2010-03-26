package com.objectmentor.library;

import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockDataServices;
import com.objectmentor.library.models.BookCopy;
import com.objectmentor.library.models.BookTitle;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.models.Receipt;

public class BookCatalogWithOneCopyTest extends TestCase {

	private BookCatalog catalog;

	protected void setUp() throws Exception {
		MockDataServices fakeBookRepository = new MockDataServices();
		catalog = new BookCatalog(fakeBookRepository);
		BookTitle bookTitle = new BookTitle("ISBN");
		fakeBookRepository.setBookToReturn(bookTitle);
		catalog.addCopy("ISBN");
	}

	public void testFindCopyShouldReturnNullForUnaddedBooks() {
		assertNull("expected null for unrecognized ISBN", catalog
				.findCopy("NOT ISBN"));
	}

	public void testFindCopyShouldReturnCopyForCorrectISBN() {
		assertNotNull("expected copy for recognized ISBN", catalog
				.findCopy("ISBN"));
	}

	public void testContainsTitleShouldReturnTrueForCorrectISBN()
			throws Exception {
		assertTrue("expected true for recognized ISBN", catalog
				.containsTitle("ISBN"));
	}

	public void testContainsTitleShouldReturnFalseForIncorrectISBN()
			throws Exception {
		assertFalse("expected false for unrecognized ISBN", catalog
				.containsTitle("NOT ISBN"));
	}

	public void testFindAvailableCopyReturnsNullWhenCopyIsBorrowed()
			throws Exception {
		List<BookCopy> copies = catalog.findAllCopies("ISBN");
		BookCopy copy = (BookCopy) copies.get(0);
		copy.setBorrowed(new Receipt(new Patron("borrower"),
				Receipt.BORROW_RECEIPT));
		assertEquals(null, catalog.findAvailableCopy("ISBN"));
	}

	public void testFindAvailableCopyReturnsCopyWhenCopyIsNotBorrowed()
			throws Exception {
		assertNotNull(catalog.findAvailableCopy("ISBN"));
	}

}
