package com.objectmentor.library.models;

import junit.framework.TestCase;

public class CompactDiscTest extends TestCase {
  
  private Media cd;

  protected void setUp() throws Exception {
    cd = new Media("title","id", Media.COMPACT_DISC);
  }
  
  public void testCDShouldHaveIdAndTitle() {
    assertEquals("id", cd.getId());
    assertEquals("title", cd.getTitle());
  }
  
  public void testCDShouldComplainIfYouAskItForAuthor() {
    try {
      cd.getAuthor();
      fail();
    } catch (IllegalAccessError e) {}
  }

}
