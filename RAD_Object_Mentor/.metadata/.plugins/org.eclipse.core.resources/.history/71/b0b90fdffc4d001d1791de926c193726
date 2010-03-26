package com.objectmentor.library;

import com.objectmentor.library.models.*;
import com.objectmentor.library.data.*;

import java.util.List;

public class Catalog {

  private DataServices ds;

  public Catalog(DataServices ds) {
    this.ds = ds;
  }

  /**
   * Adds a copy of a Book to the catalog
   * @param string
   * @return
   */
  public Book add(String string) {
    //string is isbn - get the title from the isbnService
    //giving it the isbn
    BookTitle t = ds.findTitleByIsbn(string);
    if (t == null)
      throw new IsbnDoesNotExistException();
    //addBook the copy if we found it
    return ds.addBook(t);
  }

  /**
   * Finds the copy by the isbn
   * @param string
   * @return
   */
  public Book find1(String string) {
    return ds.findCopy1(string);
  }

  /**
   * Using the string as an isbn, finds all
   * copies whether borrowed or in stock
   * @param string
   * @return
   */
  public List findList(String string) {
    return ds.findMany(string);
  }

  /**
   * How many?
   * @return
   */
  public int getCount() {
    return ds.countBooks();
  }

  /**
   * Returns true if the string (isbn) passed
   * in can be found in the gateway
   * @param string
   * @return
   */
  public boolean exists(String string) {
    return ds.canFindCopy(string);
  }

  /**
   * Finds an AVAILABLE copy (i.e. one that exists
   * but is not borrowed)
   * @param string
   * @return
   */
  public Book find2(String string) {
    return ds.findAvailableCopy(string);
  }

  /**
   * Finds the copy by the id
   * @param string
   * @return
   */
  public Book find3(String string) {
    return ds.findCopy2(string);
  }
}
