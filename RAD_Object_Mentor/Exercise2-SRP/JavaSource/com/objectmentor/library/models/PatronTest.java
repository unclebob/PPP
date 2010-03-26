package com.objectmentor.library.models;

import junit.framework.TestCase;

public class PatronTest extends TestCase {

  private Patron patron;

  protected void setUp() throws Exception {
    patron = new Patron("1", "John", "Q", "Public", "street1", "street2", "city", "state", "zip", "8479991234");
  }
  
  public void testCreate() {
    assertNotNull(patron);
  }
  
  public void testFullName() {
    assertEquals("John Q. Public", patron.getFullName());
  }
  
  public void testFullNameWithNoMiddleName() {
    patron = new Patron("1", "John", null, "Public", null, null, null, null, null, null);
    assertEquals("John Public", patron.getFullName());
  }
  
  public void testAddress() {
    assertEquals("street1\nstreet2\ncity, state zip", patron.printAddress());
  }
  
  public void testAddressWithNoStreet2() {
    patron = new Patron("1", "John", "Q", "Public", "street1", null, "city", "state", "zip", "8479991234");
    assertEquals("street1\ncity, state zip", patron.printAddress());
  }

}
