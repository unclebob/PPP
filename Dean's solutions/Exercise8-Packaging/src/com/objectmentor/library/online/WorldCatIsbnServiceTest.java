package com.objectmentor.library.online;

import com.objectmentor.library.models.Book;
import com.objectmentor.library.services.IsbnService;

import junit.framework.TestCase;

public class WorldCatIsbnServiceTest extends TestCase {
  public void testLookupTheAlchemist() throws Exception {
    IsbnService wc = new WorldCatIsbnService();
    Book theAlchemist = wc.findBookByIsbn("0061122416");
    assertNotNull(theAlchemist);
    assertEquals("The alchemist", theAlchemist.getTitle());
  }

}
