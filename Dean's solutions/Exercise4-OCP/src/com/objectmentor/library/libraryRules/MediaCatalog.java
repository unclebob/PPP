package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.models.*;

import java.util.List;

public class MediaCatalog {


  private IsbnService isbnService;
  private CompactDiscService compactDiscService;
  private MediaGateway mediaGateway;

  public MediaCatalog(IsbnService isbnService, CompactDiscService compactDiscService, MediaGateway mediaGateway) {
    this.isbnService = isbnService;
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
  }

  public MediaCopy addBookCopy(String isbn) {
    Media book = getBookByIsbn(isbn);
    return mediaGateway.addCopy(book);
  }

  public List addBookCopies(String isbn, int numberOfNewBooks) {
  	Media book = getBookByIsbn(isbn);
  	return mediaGateway.addCopies(book, numberOfNewBooks);
  }
  
	private Media getBookByIsbn(String isbn) {
		Media book = isbnService.findBookByIsbn(isbn);
    if (book == null)
      throw new IsbnDoesNotExistException();
		return book;
	}

  public MediaCopy findCopy(String isbn) {
    return mediaGateway.findCopy(isbn);
  }

  public List findAllCopies(String isbn) {
    return mediaGateway.findAllCopies(isbn);
  }

  public int mediaCount() {
    return mediaGateway.mediaCount();
  }

  public boolean contains(String id) {
    return mediaGateway.contains(id);
  }

  public MediaCopy findAvailableCopy(String isbn) {
    return mediaGateway.findAvailableCopy(isbn);
  }

  public MediaCopy findCopyById(String copyId) {
    return mediaGateway.findCopyById(copyId);
  }

  public MediaCopy addCD(String cddbId) {
    Media cd = compactDiscService.findCDById(cddbId);
    return mediaGateway.addCopy(cd);
  }

	public void delete(String id) {
		mediaGateway.delete(id);
	}

	public List findAllISBNs() {
		return mediaGateway.findAllISBNs();
	}

}
