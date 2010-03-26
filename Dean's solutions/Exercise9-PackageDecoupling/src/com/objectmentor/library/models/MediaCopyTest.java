package com.objectmentor.library.models;

import junit.framework.TestCase;

public class MediaCopyTest extends TestCase {
  
  private Media media;
  private MediaCopy mediaCopy;

  protected void setUp() throws Exception {
    media = new Book("isbn", "a book", "joe author");
    mediaCopy = new MediaCopy(media, "1");
  }

  public void testEqualsShouldPassWithEqualMediaAndID() {
    assertEquals(mediaCopy, new MediaCopy(media, "1")); 
  }
  
  public void testEqualsShouldFailForNull() {
    assertFalse(mediaCopy.equals(null));
  }
  
  public void testEqualsShouldFailForWrongType() {
    assertFalse(mediaCopy.equals(new MediaCopy(media, "1"){}));
  }
  
  public void testEqualsShouldFailWithNonEqualIDs() {
    assertFalse(mediaCopy.equals(new MediaCopy(media, "2"))); 
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
