package com.objectmentor.library.services;

import junit.framework.TestCase;

import com.objectmentor.library.models.Media;

public class IsbnServiceTest extends TestCase {
  public void testLookupTheAlchemist() throws Exception {
    IsbnService wc = new IsbnService();
    Media theAlchemist = wc.findBookByIsbn("0061122416");
    assertNotNull(theAlchemist);
    assertEquals("The alchemist", theAlchemist.getTitle());
  }

}
