package com.objectmentor.library.libraryRules;

import java.util.List;

import com.objectmentor.library.gateways.CdDoesNotExistException;
import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;

public class MediaCatalog {

  private IsbnService isbnService;
  private CompactDiscService compactDiscService;
  private MediaGateway mediaGateway;

  public MediaCatalog(IsbnService isbnService, CompactDiscService compactDiscService, MediaGateway mediaGateway) {
    this.isbnService = new IsbnService();
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
  }

  public MediaCopy addBookCopy(String isbn) {
    Media book = getBookByIsbn(isbn);
    return mediaGateway.addCopy(book);
  }

  public List<MediaCopy> addBookCopies(String isbn, int numberOfNewBooks) {
    Media book = getBookByIsbn(isbn);
    return mediaGateway.addCopies(book, numberOfNewBooks);
  }

  private Media getBookByIsbn(String isbn) {
    Media book = isbnService.findBookByIsbn(isbn);
    if (book == null)
      throw new IsbnDoesNotExistException();
    return book;
  }

  public MediaCopy addCDCopy(String barCode) {
    Media cd = compactDiscService.findCDByBarCode(barCode);
    if (cd == null)
      throw new CdDoesNotExistException();
    return mediaGateway.addCopy(cd);
  }

  public List<MediaCopy> addCDCopies(String barCode, int numberOfNewCDs) {
    Media cd = compactDiscService.findCDByBarCode(barCode);
    return mediaGateway.addCopies(cd, numberOfNewCDs);
  }

	public IsbnService getIsbnService() {
		return isbnService;
	}
}
