package com.objectmentor.library.offline;

import com.objectmentor.library.mocks.MockPatronGateway;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

public class InMemoryPatronGatewayTest extends TestCase {

  InMemoryPatronGateway patronGateway = new MockPatronGateway();

  protected void setUp() throws Exception {
    patronGateway = new MockPatronGateway();
  }

  public void testCreate() {
    assertEquals(0, patronGateway.getPatronList().size());
  }

  public void testAddOne() throws Exception {
    Patron bob = new Patron(DateUtil.dateFromString("1/1/2000"));
    bob.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(bob.getId(), bob);
    assertEquals(1, patronGateway.getPatronList().size());
    Patron patron = patronGateway.getPatronMap().get(bob.getId());
    assertTrue(patron.hasId(bob.getId()));
  }

  public void testAddAFew() throws Exception {
    Patron bob = new Patron(DateUtil.dateFromString("1/1/2000"));
    bob.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(bob.getId(), bob);
    Patron tim = new Patron(DateUtil.dateFromString("1/1/2000"));
    tim.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(tim.getId(), tim);
    Patron dean = new Patron(DateUtil.dateFromString("1/1/2000"));
    dean.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(dean.getId(), dean);
    assertEquals(3, patronGateway.getPatronList().size());
    Patron patron = patronGateway.getPatronMap().get(tim.getId());
    assertTrue(patron.hasId(tim.getId()));
  }

  public void testRetrieveNonexistant() {
    assertNull(patronGateway.getPatronMap().get("nonesuch"));
  }
}
