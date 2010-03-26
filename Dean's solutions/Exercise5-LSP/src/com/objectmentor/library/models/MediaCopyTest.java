package com.objectmentor.library.models;

import junit.framework.TestCase;

public class MediaCopyTest extends TestCase {
  
  private Book book;
  private MediaCopy mediaCopy;

  protected void setUp() throws Exception {
    book = new Book("isbn", "a book", "joe author");
    mediaCopy = new MediaCopy(book, "1");
  }

  public void testEqualsShouldPassWithEqualMediaAndID() {
    assertEquals(mediaCopy, new MediaCopy(book, "1")); 
  }
  
  public void testEqualsShouldFailForNull() {
    assertFalse(mediaCopy.equals(null));
  }
  
  public void testEqualsShouldFailForWrongType() {
    assertFalse(mediaCopy.equals(new MediaCopy(book, "1"){}));
  }
  
  public void testEqualsShouldFailWithNonEqualIDs() {
    assertFalse(mediaCopy.equals(new MediaCopy(book, "2"))); 
  }
  
  public void testEqualsShouldFailWithNonEqualMedia() {
    assertFalse(mediaCopy.equals(new MediaCopy(new CompactDisc("barcode", "title", "artist"), "1"))); 
  }
  
  public void testGetTitleShouldGetTitleFromMedia() {
    assertEquals("a book", mediaCopy.getTitle());
  }
  
  public void testGetAuthorShouldGetAuthorFromMedia() {
    assertEquals("joe author", mediaCopy.getAuthor());
  }
}
