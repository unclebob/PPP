package com.objectmentor.library.application.models;

import com.objectmentor.library.application.libraryRules.Damageable;
import junit.framework.TestCase;

public class CompactDiscTest extends TestCase {

  private Damageable compactDisc;

  protected void setUp() {
    compactDisc = new CompactDisc("barcode", "title", "artist");
  }

  public void testEqualityShouldPassWithLikeValues() {
    assertEquals(compactDisc, new CompactDisc("barcode", "title", "artist"));
  }

  public void testEqualityShouldFailOnNull() {
    assertFalse(compactDisc.equals(null));
  }

  public void testEqualityShouldFailOnWrongType() {
    assertFalse(compactDisc.equals(new CompactDisc("barcode", "title", "artist") {
    }));
  }

  public void testEqualityShouldFailOnWrongTitle() {
    assertFalse(compactDisc.equals(new CompactDisc("barcode", "title 2", "artist")));
  }

  public void testEqualityShouldFailOnWrongArtist() {
    assertFalse(compactDisc.equals(new CompactDisc("barcode", "title", "artist 2")));
  }
}
