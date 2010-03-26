package com.objectmentor.library;

import com.objectmentor.library.data.DataServices;
import com.objectmentor.library.data.IsbnDoesNotExistException;
import com.objectmentor.library.models.*;

import java.util.List;

public class BookCatalog {

  private DataServices dataServices;

  public BookCatalog(DataServices dataServices) {
    this.dataServices = dataServices;
  }

  public BookCopy addCopy(String isbn) {
    BookTitle bookTitle = dataServices.findTitleByIsbn(isbn);
    if (bookTitle == null)
      throw new IsbnDoesNotExistException();
    return dataServices.addCopy(bookTitle);
  }

  public BookCopy findCopy(String isbn) {
    return dataServices.findCopy(isbn);
  }

  public List findAllCopies(String isbn) {
    return dataServices.findAllCopies(isbn);
  }

  public int bookCount() {
    return dataServices.bookCount();
  }

  public boolean containsTitle(String isbn) {
    return dataServices.containsTitle(isbn);
  }

  public BookCopy findAvailableCopy(String isbn) {
    return dataServices.findAvailableCopy(isbn);
  }

  public BookCopy findCopyById(String copyId) {
    return dataServices.findCopyById(copyId);
  }
}
