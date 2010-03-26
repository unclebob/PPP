package com.objectmentor.library.models;

import junit.framework.TestCase;

public class PatronTest extends TestCase {

  public void testCreate() {
    Address address = new Address("street1", "street2", "city", "state", "zip");
    assertNotNull(new Patron("1", "John", "Q", "Public", address, "8479991234"));
  }
  

}
