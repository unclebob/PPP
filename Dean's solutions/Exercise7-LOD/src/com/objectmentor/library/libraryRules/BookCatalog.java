package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.services.*;

import java.util.List;

public class BookCatalog {

  private IsbnService isbnService;
  private BookGateway bookGateway;

  public BookCatalog(IsbnService isbnService, BookGateway bookGateway) {
    this.isbnService = isbnService;
    this.bookGateway = bookGateway;
  }

  public MediaCopy addBookCopy(String isbn) {
    Book book = getBookByIsbn(isbn);
    return bookGateway.addCopy(book);
  }

  public List addBookCopies(String isbn, int numberOfNewBooks) {
    Book book = getBookByIsbn(isbn);
    return bookGateway.addCopies(book, numberOfNewBooks);
  }

  private Book getBookByIsbn(String isbn) {
    Book book = isbnService.findBookByIsbn(isbn);
    if (book == null)
      throw new IsbnDoesNotExistException();
    return book;
  }

}