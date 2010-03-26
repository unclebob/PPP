package com.objectmentor.library.mocks;

import com.objectmentor.library.data.PatronDoesNotExistException;
import com.objectmentor.library.models.Patron;
import junit.framework.TestCase;

public class MockDataServicesPatronAccessTest extends TestCase {

  MockDataServices patrons;

  protected void setUp() throws Exception {
    patrons = new MockDataServices();
  }

  public void testCreate() {
    assertEquals(0, patrons.countActivePatrons());
  }

  public void testAddOne() throws PatronDoesNotExistException {
    patrons.addPatron(new Patron("Bob"));
    assertEquals(1, patrons.countActivePatrons());
    Patron bob = patrons.findPatronById("Bob");
    assertTrue(bob.hasId("Bob"));
  }

  public void testAddAFew() throws PatronDoesNotExistException {
    patrons.addPatron(new Patron("Bob"));
    patrons.addPatron(new Patron("Tim"));
    patrons.addPatron(new Patron("Dean"));
    assertEquals(3, patrons.countActivePatrons());
    Patron bob = patrons.findPatronById("Tim");
    assertTrue(bob.hasId("Tim"));
  }

  public void testRetrieveNonexistant() {
    assertNull(patrons.findPatronById("nonesuch"));
  }

}
