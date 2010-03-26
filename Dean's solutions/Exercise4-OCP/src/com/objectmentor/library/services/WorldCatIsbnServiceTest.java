package com.objectmentor.library.services;

import com.objectmentor.library.libraryRules.IsbnService;
import com.objectmentor.library.models.Media;

import junit.framework.TestCase;

public class WorldCatIsbnServiceTest extends TestCase {
  public void testLookupTheAlchemist() throws Exception {
    IsbnService wc = new WorldCatIsbnService();
    Media theAlchemist = wc.findBookByIsbn("0061122416");
    assertNotNull(theAlchemist);
    assertEquals("The alchemist", theAlchemist.getTitle());
  }

}
