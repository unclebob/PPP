package com.objectmentor.library.reports;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.models.Media;

public class LoanedBookFormatReportTest extends TestCase {
	
	private MediaGateway gateway;
	private LoanedBookFormatReport report;
	private boolean LOANED = true;
	private boolean NOT_LOANED = false;

	protected void setUp() throws Exception {
		gateway = new MockMediaGateway();
		report = new LoanedBookFormatReport(gateway);
	}

	public void testReportWithNoBooks() {
		report.generate();
		assertEquals(0, report.getNumberOfHardCoverBooks());
		assertEquals(0, report.getNumberOfSoftCoverBooks());
	}
	
	public void testReportWithOneHardcoverBook() {
		addBook(Book.HARD_COVER, LOANED);
		addBook(Book.HARD_COVER, NOT_LOANED);
		report.generate();
		assertEquals(1, report.getNumberOfHardCoverBooks());
		assertEquals(0, report.getNumberOfSoftCoverBooks());
	}

	public void testReportWithOneSoftcoverBook() {
		addBook(Book.SOFT_COVER, LOANED);
		addBook(Book.SOFT_COVER, NOT_LOANED);
		report.generate();
		assertEquals(0, report.getNumberOfHardCoverBooks());
		assertEquals(1, report.getNumberOfSoftCoverBooks());
	}
	
	public void testReportWithManyOfBoth() {
		addManyBooks(10, Book.HARD_COVER, LOANED);
		addManyBooks(11, Book.HARD_COVER, NOT_LOANED);
		addManyBooks(12, Book.SOFT_COVER, LOANED);
		addManyBooks(13, Book.SOFT_COVER, NOT_LOANED);
		report.generate();
		assertEquals(10, report.getNumberOfHardCoverBooks());
		assertEquals(12, report.getNumberOfSoftCoverBooks());
	}
	
	public void testReportWithNoBooksButSomeCompactDiscs() {
		for (int i=0; i< 5; i++) {
			CompactDisc disc = new CompactDisc("disc"+i, "Title "+i, "Author "+i);
			MediaCopy copy = gateway.addCopy(disc);
			copy.setLoaned(new LoanReceipt(null));
		}
		report.generate();
		assertEquals(0, report.getNumberOfHardCoverBooks());
		assertEquals(0, report.getNumberOfSoftCoverBooks());
	}

	private void addManyBooks(int n, int format, boolean loaned) {
		for (int i = 0; i < n; i++) {
			addBook(format, loaned);
		}
	}

	private void addBook(int format, boolean loan) {
		Book book = new Book("isbn", "title", "author");
		book.setFormat(format);
		MediaCopy copy = gateway.addCopy(book);
		if (loan)
			copy.setLoaned(new LoanReceipt(null));
	}
}
