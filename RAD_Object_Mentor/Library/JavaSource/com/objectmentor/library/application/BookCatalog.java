package com.objectmentor.library.application;

import java.util.List;

import com.objectmentor.library.application.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.application.gateways.MediaGateway;
import com.objectmentor.library.application.models.Book;
import com.objectmentor.library.application.models.MediaCopy;
import com.objectmentor.library.application.services.IsbnService;

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