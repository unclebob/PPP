package com.objectmentor.library.data;

import com.objectmentor.library.models.*;

import java.util.List;

public interface DataServices {
  //ADD FUNCTIONS------------------------
  BookCopy addCopy(BookTitle bookTitle);

  void addPatron(Patron patron);

  //FIND FUNCTIONS------------------------
  BookCopy findCopy(String isbn);

  BookTitle findTitleByIsbn(String isbn);

  List findAllCopies(String isbn);

  BookCopy findAvailableCopy(String isbn);

  BookCopy findCopyById(String copyId);

  Patron findPatronById(String id);

  //BOOLEAN QUERIES-----------------------
  boolean containsTitle(String isbn);

  //COUNT QUERIES-------------------------
  int countActivePatrons();

  int bookCount();
}