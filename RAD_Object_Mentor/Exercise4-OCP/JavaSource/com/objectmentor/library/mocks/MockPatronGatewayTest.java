package com.objectmentor.library.mocks;

import com.objectmentor.library.models.Address;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

public class MockPatronGatewayTest extends TestCase {

  MockPatronGateway patrons = new MockPatronGateway();

  protected void setUp() throws Exception {
    patrons = new MockPatronGateway();
  }

  public void testCreate() {
    assertEquals(0, patrons.countActive());
  }

  public void testAddOne() throws Exception {
    Patron bob = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(bob);
    assertEquals(1, patrons.countActive());
    Patron patron = patrons.findById(bob.getId());
    assertTrue(patron.hasId(bob.getId()));
  }

  public void testAddAFew() throws Exception {
    Patron bob = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(bob);
    Patron tim = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(tim);
    Patron dean = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(dean);
    assertEquals(3, patrons.countActive());
    Patron patron = patrons.findById(tim.getId());
    assertTrue(patron.hasId(tim.getId()));
  }

  public void testModify() throws Exception {
		Address address = new Address("street1", "street2", "city", "state", "zip");
    Patron bob = new Patron("Bob", "", "Martin", address, "");
    patrons.add(bob);
    assertEquals(1, patrons.countActive());
    Patron bob2 = new Patron("Bob2", "", "Martin", address, "");
    bob2.setId(bob.getId());
    patrons.modify(bob2);
    assertEquals(1, patrons.countActive());
    Patron bobDuex = patrons.findById(bob.getId());
    assertEquals("Bob2", bobDuex.getFirstName());
  }

  public void testDeleteAFew() throws Exception {
    Patron bob = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(bob);
    Patron tim = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(tim);
    Patron dean = new Patron(DateUtil.dateFromString("1/1/2000"));
    patrons.add(dean);
    assertEquals(3, patrons.countActive());
    patrons.delete(bob.getId());
    assertEquals(2, patrons.countActive());
    patrons.delete(dean.getId());
    assertEquals(1, patrons.countActive());
    patrons.delete(tim.getId());
    assertEquals(0, patrons.countActive());
  }

  public void testRetrieveNonexistant() {
    assertNull(patrons.findById("nonesuch"));
  }

}
