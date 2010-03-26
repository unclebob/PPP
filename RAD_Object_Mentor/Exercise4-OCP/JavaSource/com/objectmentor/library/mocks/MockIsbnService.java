package com.objectmentor.library.mocks;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.libraryRules.IsbnService;
import com.objectmentor.library.models.Media;

public class MockIsbnService implements IsbnService {
  public String isbnLastPassedToFind;
  Map<String, Media> books = new HashMap<String, Media>();


  public void setBookToReturn(Media book) {
    books.put(book.getId(), book);
  }

  public Media findBookByIsbn(String isbn) {
    isbnLastPassedToFind = isbn;
    return books.get(isbn);
  }
}
