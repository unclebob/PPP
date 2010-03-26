package com.objectmentor.library.reports;

import java.util.Iterator;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class LoanedBookFormatReport {

	private final MediaGateway gateway;
	private int numberHardCover;
	private int numberSoftCover;

	public LoanedBookFormatReport(MediaGateway gateway) {
		this.gateway = gateway;
	}

	public void generate() {
		numberHardCover = 0;
		numberSoftCover = 0;
		for (Iterator i = gateway.findAllCopiesByType(Book.class).iterator(); i.hasNext();) {
			MediaCopy bookCopy = (MediaCopy) i.next();
			if (bookCopy.isLoaned()) {
				Book book = (Book) bookCopy.getMedia();
				if (book.usesFormat(Book.HARD_COVER))
					numberHardCover++;
				else if (book.usesFormat(Book.SOFT_COVER))
					numberSoftCover++;
			}
		}
	}

	public int getNumberOfHardCoverBooks() {
		return numberHardCover;
	}

	public int getNumberOfSoftCoverBooks() {
		return numberSoftCover;
	}

}
