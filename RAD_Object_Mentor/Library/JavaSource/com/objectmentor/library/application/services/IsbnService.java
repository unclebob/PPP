package com.objectmentor.library.application.services;

import com.objectmentor.library.application.models.Book;

public interface IsbnService {
  Book findBookByIsbn(String isbn);

  void clear();
}
