package com.objectmentor.library.reports;

import java.util.Iterator;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.Media;

public class BookFormatReport {

	private final MediaGateway gateway;
	private int numberHardCover;
	private int numberSoftCover;

	public BookFormatReport(MediaGateway gateway) {
		this.gateway = gateway;
	}

	public void generate() {
		numberHardCover = 0;
		numberSoftCover = 0;
		for (Iterator i = gateway.findAllCopies().iterator(); i.hasNext();) {
			MediaCopy bookCopy = (MediaCopy) i.next();
			if (bookCopy.isLoaned()) {
				Media book = bookCopy.getMedia();
				if (book.usesFormat(Media.HARD_COVER))
					numberHardCover++;
				else if (book.usesFormat(Media.SOFT_COVER))
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
