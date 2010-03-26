package com.objectmentor.library;

import com.objectmentor.library.mocks.*;
import junit.framework.TestCase;

public class BookCatalogWhenEmptyTest extends TestCase {

  private MockDataServices fakeBookRepository;
  private BookCatalog catalog;

  protected void setUp() throws Exception {
    fakeBookRepository = new MockDataServices();
    catalog = new BookCatalog(fakeBookRepository);
  }

  public void testShouldBeEmpty() {
    assertEquals("expected empty catalog", 0, catalog.bookCount());
  }

  public void testContainsTitleShouldReturnFalse() throws Exception {
    assertFalse(catalog.containsTitle("book"));
  }

  public void testFindAvailableCopyShouldReturnNull() throws Exception {
    assertNull(catalog.findAvailableCopy("book"));
  }

}
