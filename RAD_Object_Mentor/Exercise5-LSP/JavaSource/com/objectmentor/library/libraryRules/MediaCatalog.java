package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.services.*;

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
    Book book = getBookByIsbn(isbn);
    return mediaGateway.addCopy(book);
  }

  public List<MediaCopy> addBookCopies(String isbn, int numberOfNewBooks) {
    Book book = getBookByIsbn(isbn);
    return mediaGateway.addCopies(book, numberOfNewBooks);
  }

  private Book getBookByIsbn(String isbn) {
    Book book = isbnService.findBookByIsbn(isbn);
    if (book == null)
      throw new IsbnDoesNotExistException();
    return book;
  }

  public MediaCopy addCDCopy(String barCode) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    if (cd == null)
      throw new CdDoesNotExistException();
    return mediaGateway.addCopy(cd);
  }

  public List<MediaCopy> addCDCopies(String barCode, int numberOfNewCDs) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    return mediaGateway.addCopies(cd, numberOfNewCDs);
  }
}
