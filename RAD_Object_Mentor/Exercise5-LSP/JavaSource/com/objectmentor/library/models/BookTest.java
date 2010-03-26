package com.objectmentor.library.models;

import junit.framework.TestCase;

public class BookTest extends TestCase {
	
	public void testBookFormatIsSoftCoverByDefault() {
		Media book = new Book("isbn","title","author");
		assertTrue(book.usesFormat(Media.SOFT_COVER));
	}

	public void testBookFormatIsHardCoverIfSet() {
		Media book = new Book("isbn","title","author");
		book.setFormat(Media.HARD_COVER);
		assertTrue(book.usesFormat(Media.HARD_COVER));
		assertFalse(book.usesFormat(Media.SOFT_COVER));
	}

}
