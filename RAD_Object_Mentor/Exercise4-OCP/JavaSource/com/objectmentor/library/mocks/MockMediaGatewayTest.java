package com.objectmentor.library.mocks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class MockMediaGatewayTest extends TestCase {
  
	private MockMediaGateway gateway;
	private String id1;
	private String id2;

	protected void setUp() throws Exception {
		super.setUp();
		gateway = new MockMediaGateway();
		id1 = gateway.addCopy(new Media("isbn", "War and Peace", "Tolstoy")).getId();
		id2 = gateway.addCopy(new Media("isbn", "War and Peace", "Tolstoy")).getId();
	}

	public void testAddCopyCreateUniqueId() throws Exception {
    assertFalse(id1.equals(id2));
  }

	public void testAddCopiesCreatesUniqueIds() throws Exception {
		List<MediaCopy> copies = gateway.addCopies(new Media("isbn", "War and Peace", "Tolstoy"), 3);
		assertEquals(3, copies.size());
		assertEquals(5, gateway.findAllCopies("isbn").size());
		Set<String> ids = new HashSet<String>();
		for (Iterator<?> iter = gateway.findAllCopies("isbn").iterator(); iter.hasNext(); )
			ids.add(((MediaCopy) iter.next()).getId());
    assertEquals(5, ids.size());
  }

  public void testGetAllISBNs() {
  	gateway.addCopy(new Media("isbn2", "Peace and War", "Toy Story")).getId();
  	List<String> isbns = gateway.findAllISBNs();
  	assertEquals(2, isbns.size());
  	Iterator<String> iter = isbns.iterator();
  	while (iter.hasNext())
  		assertTrue(iter.next().startsWith("isbn"));
  }
}
