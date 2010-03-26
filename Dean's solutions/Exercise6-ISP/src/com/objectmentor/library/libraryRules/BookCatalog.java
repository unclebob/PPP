package com.objectmentor.library.libraryRules;

import java.util.List;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.services.IsbnService;

public class BookCatalog {

  private IsbnService isbnService;
  private MediaGateway mediaGateway;

  public BookCatalog(IsbnService isbnService, MediaGateway mediaGateway) {
    this.isbnService = isbnService;
    this.mediaGateway = mediaGateway;
  }

  public MediaCopy addBookCopy(String isbn) {
    Book book = getBookByIsbn(isbn);
    return mediaGateway.addCopy(book);
  }

  public List addBookCopies(String isbn, int numberOfNewBooks) {
    Book book = getBookByIsbn(isbn);
    return mediaGateway.addCopies(book, numberOfNewBooks);
  }

  private Book getBookByIsbn(String isbn) {
    Book book = isbnService.findBookByIsbn(isbn);
    if (book == null)
      throw new IsbnDoesNotExistException();
    return book;
  }

}
