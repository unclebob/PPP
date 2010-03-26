package com.objectmentor.library.mocks;

import com.objectmentor.library.models.Book;
import com.objectmentor.library.offline.InMemoryIsbnService;
import com.objectmentor.library.services.IsbnService;

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
