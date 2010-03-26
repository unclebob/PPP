package com.objectmentor.library.models;

import junit.framework.TestCase;

public class BookTest extends TestCase {
  Media book;
  
  protected void setUp() throws Exception {
    book = new Media("id","title","author");
  }
  
  public void testBookShouldHaveTitleAndIdAndAuthor() {
    assertEquals("id", book.getId());
    assertEquals("title", book.getTitle());
    assertEquals("author", book.getAuthor());
  }
  
}
