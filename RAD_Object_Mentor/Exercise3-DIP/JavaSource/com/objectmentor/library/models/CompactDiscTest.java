package com.objectmentor.library.models;

import junit.framework.TestCase;

import com.objectmentor.library.libraryRules.Damageable;

public class CompactDiscTest extends TestCase {
  
  private Damageable cd;

  protected void setUp() {
    cd = new Media("barcode", "title", "artist", Media.COMPACT_DISC);
  }

  public void testEqualityShouldPassWithLikeValues() {
    assertEquals(cd, new Media("barcode", "title", "artist", Media.COMPACT_DISC));
  }
  
  public void testEqualityShouldFailOnNull() {
    assertFalse(cd.equals(null));
  }
  
  public void testEqualityShouldFailOnWrongType() {
    assertFalse(cd.equals(new Media("barcode", "title", "artist", Media.COMPACT_DISC){}));
  }
  
  public void testEqualityShouldFailOnWrongTitle() {
    assertFalse(cd.equals(new Media("barcode", "title 2", "artist", Media.COMPACT_DISC)));
  }
  
  public void testEqualityShouldFailOnWrongArtist() {
    assertFalse(cd.equals(new Media("barcode", "title", "artist 2", Media.COMPACT_DISC)));
  }
}
