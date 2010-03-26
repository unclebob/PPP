package com.objectmentor.library.services;

import com.objectmentor.library.models.Book;

public interface IsbnService {
  Book findBookByIsbn(String isbn);

  void clear();
}
