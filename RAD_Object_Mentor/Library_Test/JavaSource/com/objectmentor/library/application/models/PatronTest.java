package com.objectmentor.library.application.models;

import junit.framework.TestCase;

public class PatronTest extends TestCase {

  private Address address;
  private String phone;

  protected void setUp() throws Exception {
    super.setUp();
    address = new Address("street1", "street2", "city", "state", "zip");
    phone = "8479991234";
  }

  public void testCreate() {
    Patron patron = new Patron("John", "Q", "Public", address, phone);
    assertEquals("John Q. Public", patron.getFullName());
    assertEquals(address, patron.getAddress());
    assertEquals(phone, patron.getPhone());
  }

  public void testGetFullName() {
    assertEquals("John Q. Public", new Patron("John", "Q", "Public", address, phone).getFullName());
    assertEquals("John Public", new Patron("John", "", "Public", address, phone).getFullName());
    assertEquals("John Q. ", new Patron("John", "Q", "", address, phone).getFullName());
    assertEquals("Public", new Patron("", "", "Public", address, phone).getFullName());
    assertEquals("John ", new Patron("John", "", "", address, phone).getFullName());
  }
}
