package com.objectmentor.library.services;

import junit.framework.TestCase;

import com.objectmentor.library.models.Media;

public class IsbnServiceTest extends TestCase {
  public void testLookupBook() throws Exception {
    IsbnService wc = new IsbnService();
    Media theAlchemist = wc.findBookByIsbn("0061122416");
    assertNotNull(theAlchemist);
    assertEquals("Title61122416", theAlchemist.getTitle());
  }

}
