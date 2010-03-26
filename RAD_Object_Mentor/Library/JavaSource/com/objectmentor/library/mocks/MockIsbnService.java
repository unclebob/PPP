package com.objectmentor.library.mocks;

import com.objectmentor.library.application.models.Book;
import com.objectmentor.library.application.services.IsbnService;
import com.objectmentor.library.offline.InMemoryIsbnService;

public class MockIsbnService extends InMemoryIsbnService implements IsbnService {
  public String isbnLastPassedToFind;

  public Book findBookByIsbn(String isbn) {
    isbnLastPassedToFind = isbn;
    return super.findBookByIsbn(isbn);
  }

  public void setBookToReturn(Book book) {
    addBook(book);
  }

}
