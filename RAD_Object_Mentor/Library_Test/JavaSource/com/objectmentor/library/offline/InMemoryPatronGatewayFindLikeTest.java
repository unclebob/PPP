package com.objectmentor.library.offline;

import com.objectmentor.library.application.gateways.PatronGateway;
import com.objectmentor.library.application.models.Patron;
import com.objectmentor.library.mocks.MockPatronGateway;
import junit.framework.TestCase;

import java.util.Set;

public class InMemoryPatronGatewayFindLikeTest extends TestCase {
  private PatronGateway patrons;
  private Patron bob;
  private Patron dean;
  private Patron david;
  private Patron james;
  private Patron tim;
  private Patron libby;

  protected void setUp() throws Exception {
    patrons = new MockPatronGateway();
    bob = new Patron("Robert", "C", "Martin", null, null);
    dean = new Patron("Dean", "J", "Wamper", null, null);
    david = new Patron("David", "A", "Chelimsky", null, null);
    james = new Patron("James", "W", "Grenning", null, null);
    tim = new Patron("Tim", "O", "Ottinger", null, null);
    libby = new Patron("Libby", "R", "Ottinger", null, null);
    patrons.add(bob);
    patrons.add(dean);
    patrons.add(david);
    patrons.add(james);
    patrons.add(tim);
    patrons.add(libby);
  }

  public void testFindPatronsLikeMatchesSingleFirstName() throws Exception {
    Set patronsLike = patrons.findLike("R");
    assertTrue(patronsLike.contains(bob));
    assertEquals(1, patronsLike.size());
  }

  public void testFindPatronsLikeMatchesTwoLastNames() throws Exception {
    Set patronsLike = patrons.findLike("Ott");
    assertEquals(2, patronsLike.size());
    assertTrue(patronsLike.contains(libby) && patronsLike.contains(tim));
  }

  public void testFindPatronsLikeMatchesTwoFirstNames() throws Exception {
    Set patronsLike = patrons.findLike("D");
    assertEquals(2, patronsLike.size());
    assertTrue(patronsLike.contains(david) && patronsLike.contains(dean));
  }

  public void testFindPatronsLikeMatchesOneFirstNameAtSecondLetter() throws Exception {
    Set patronsLike = patrons.findLike("De");
    assertEquals(1, patronsLike.size());
    assertTrue(patronsLike.contains(dean));
  }


}
