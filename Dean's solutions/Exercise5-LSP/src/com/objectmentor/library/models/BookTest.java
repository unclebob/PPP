package com.objectmentor.library.models;

import junit.framework.TestCase;

public class BookTest extends TestCase {
	
	public void testBookFormatIsSoftCoverByDefault() {
		Book book = new Book("isbn","title","author");
		assertTrue(book.usesFormat(Book.SOFT_COVER));
	}

	public void testBookFormatIsHardCoverIfSet() {
		Book book = new Book("isbn","title","author");
		book.setFormat(Book.HARD_COVER);
		assertTrue(book.usesFormat(Book.HARD_COVER));
		assertFalse(book.usesFormat(Book.SOFT_COVER));
	}

}
