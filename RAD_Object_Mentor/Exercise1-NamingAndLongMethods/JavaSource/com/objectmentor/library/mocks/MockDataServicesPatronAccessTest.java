package com.objectmentor.library.mocks;

import com.objectmentor.library.data.PatronDoesNotExistException;
import com.objectmentor.library.models.Patron;
import junit.framework.TestCase;

public class MockDataServicesPatronAccessTest extends TestCase {

  MockDataServices ds = new MockDataServices();

  protected void setUp() throws Exception {
    ds = new MockDataServices();
  }

  public void testCreate() {
    assertEquals(0, ds.countActivePatrons());
  }

  public void testAddOne() throws PatronDoesNotExistException {
    ds.addPatron(new Patron("Bob"));
    assertEquals(1, ds.countActivePatrons());
    Patron p1 = ds.findPatron("Bob");
    assertTrue(p1.hasId("Bob"));
  }

  public void testAddAFew() throws PatronDoesNotExistException {
    ds.addPatron(new Patron("Bob"));
    ds.addPatron(new Patron("Tim"));
    ds.addPatron(new Patron("Dean"));
    assertEquals(3, ds.countActivePatrons());
    Patron p1 = ds.findPatron("Tim");
    assertTrue(p1.hasId("Tim"));
  }

  public void testRetrieveNonexistant() {
    assertNull(ds.findPatron("nonesuch"));
  }

}
