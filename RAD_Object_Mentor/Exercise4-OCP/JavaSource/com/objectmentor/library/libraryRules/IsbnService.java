package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Media;

public interface IsbnService {
  Media findBookByIsbn(String isbn);
}
