package com.objectmentor.library.reports;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.models.Media;

public class BookFormatReportTest extends TestCase {
	
	private MediaGateway gateway;
	private BookFormatReport report;
	private boolean LOANED = true;
	private boolean NOT_LOANED = false;

	protected void setUp() throws Exception {
		gateway = new MockMediaGateway();
		report = new BookFormatReport(gateway);
	}

	public void testReportWithNoBooks() {
		report.generate();
		assertEquals(0, report.getNumberOfHardCoverBooks());
		assertEquals(0, report.getNumberOfSoftCoverBooks());
	}
	
	public void testReportWithOneHardcoverBook() {
		addBook(Media.HARD_COVER, LOANED);
		addBook(Media.HARD_COVER, NOT_LOANED);
		report.generate();
		assertEquals(1, report.getNumberOfHardCoverBooks());
		assertEquals(0, report.getNumberOfSoftCoverBooks());
	}

	public void testReportWithOneSoftcoverBook() {
		addBook(Media.SOFT_COVER, LOANED);
		addBook(Media.SOFT_COVER, NOT_LOANED);
		report.generate();
		assertEquals(0, report.getNumberOfHardCoverBooks());
		assertEquals(1, report.getNumberOfSoftCoverBooks());
	}
	
	public void testReportWithManyOfBoth() {
		addManyBooks(10, Media.HARD_COVER, LOANED);
		addManyBooks(11, Media.HARD_COVER, NOT_LOANED);
		addManyBooks(12, Media.SOFT_COVER, LOANED);
		addManyBooks(13, Media.SOFT_COVER, NOT_LOANED);
		report.generate();
		assertEquals(10, report.getNumberOfHardCoverBooks());
		assertEquals(12, report.getNumberOfSoftCoverBooks());
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
