package com.objectmentor.library.mocks;

import com.objectmentor.library.models.BookTitle;
import junit.framework.TestCase;

public class MockDataServicesBookAccessTest extends TestCase {

  public void testAddCopyCreateUniqueId() throws Exception {
    MockDataServices g = new MockDataServices();
    String id1 = g.addBook(new BookTitle("isbn")).getId();
    String id2 = g.addBook(new BookTitle("isbn")).getId();
    assertFalse(id1.equals(id2));
  }

}
