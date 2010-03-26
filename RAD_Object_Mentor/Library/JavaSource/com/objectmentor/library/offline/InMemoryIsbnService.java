package com.objectmentor.library.offline;

import com.objectmentor.library.application.models.Book;
import com.objectmentor.library.application.services.IsbnService;

import java.util.*;

public class InMemoryIsbnService implements IsbnService {

  private Map books = new HashMap();

  public void addBook(Book book) {
    books.put(book.getId(), book);
  }

  public Book findBookByIsbn(String isbn) {
    return (Book) books.get(isbn);
  }

  public void clear() {
    books.clear();
  }

}