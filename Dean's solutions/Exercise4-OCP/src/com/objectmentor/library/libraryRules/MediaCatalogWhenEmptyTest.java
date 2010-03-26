package com.objectmentor.library.libraryRules;

import com.objectmentor.library.mocks.*;
import junit.framework.TestCase;

public class MediaCatalogWhenEmptyTest extends TestCase {
  private MediaCatalog catalog;

  protected void setUp() throws Exception {
    MockIsbnService isbnService = new MockIsbnService();
    MockMediaGateway mockBookRepository = new MockMediaGateway();
    catalog = new MediaCatalog(isbnService, null, mockBookRepository);
  }

  public void testShouldBeEmpty() {
    assertEquals("expected empty catalog", 0, catalog.mediaCount());
  }

  public void testContainsShouldReturnFalse() throws Exception {
    assertFalse(catalog.contains("book"));
  }

  public void testFindAvailableCopyShouldReturnNull() throws Exception {
    assertNull(catalog.findAvailableCopy("book"));
  }
}
