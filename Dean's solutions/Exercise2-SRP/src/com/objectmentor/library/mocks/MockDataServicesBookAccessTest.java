package com.objectmentor.library.mocks;

import com.objectmentor.library.models.BookTitle;
import junit.framework.TestCase;

public class MockDataServicesBookAccessTest extends TestCase {

  public void testAddCopyShouldCreateUniqueId() throws Exception {
    MockDataServices dataServices = new MockDataServices();
    String id1 = dataServices.addCopy(new BookTitle("isbn")).getId();
    String id2 = dataServices.addCopy(new BookTitle("isbn")).getId();
    assertFalse(id1.equals(id2));
  }

}
