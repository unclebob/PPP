package com.objectmentor.library.offline;

import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.services.IsbnService;

import java.util.*;

public class InMemoryIsbnService implements IsbnService {

  Map<String, Media> books = new HashMap<String, Media>();

  public void addBook(Media book) {
    books.put(book.getId(), book);
  }

  public Book findBookByIsbn(String isbn) {
    return (Book) books.get(isbn);
  }

  public void clear() {
    books.clear();
  }

}