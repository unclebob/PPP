package com.objectmentor.library.offline;

import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

import java.util.*;
import java.util.Map.Entry;

public class InMemoryMediaGatewayTest extends TestCase {

  private InMemoryMediaGateway gateway;
  private String id1;
  private String id2;
  private MediaCopy copy1;
  private MediaCopy copy2;

  protected void setUp() throws Exception {
    super.setUp();
    gateway = new MockMediaGateway();
    copy1 = gateway.addCopy(new Book("isbn", "War and Peace", "Tolstoy"));
    id1 = copy1.getId();
    copy2 = gateway.addCopy(new Book("isbn", "War and Peace", "Tolstoy"));
    id2 = copy2.getId();
  }

  public void testAddCopyCreateUniqueId() throws Exception {
    assertFalse(id1.equals(id2));
  }

  public void testAddCopiesCreatesUniqueIds() throws Exception {
    List copies = gateway.addCopies(new Book("isbn", "War and Peace", "Tolstoy"), 3);
    assertEquals(3, copies.size());
    assertEquals(5, gateway.findAllCopiesByIsbn("isbn").size());
    Set ids = new HashSet();
    for (Iterator iter = gateway.findAllCopiesByIsbn("isbn").iterator(); iter.hasNext();)
      ids.add(((MediaCopy) iter.next()).getId());
    assertEquals(5, ids.size());
  }

  public void testGetAllISBNs() {
    gateway.addCopy(new Book("isbn2", "Peace and War", "Toy Story")).getId();
    List isbns = gateway.findAllISBNs();
    assertEquals(2, isbns.size());
    Iterator iter = isbns.iterator();
    while (iter.hasNext()) {
      String isbn = (String) iter.next();
      assertTrue(isbn, isbn.startsWith("isbn"));
    }
  }

  public void testGetAllISBNsAndTitles() {
    gateway.addCopy(new Book("isbn2", "Peace and War", "Toy Story")).getId();
    gateway.addCopy(new Book("isbn3", "Son of Peace and War", "Toy Story II")).getId();
    Map isbnsTitles = gateway.findAllISBNsAndTitles();
    assertEquals(3, isbnsTitles.size());
    Iterator iter = isbnsTitles.entrySet().iterator();
    while (iter.hasNext()) {
      Entry entry = (Entry) iter.next();
      assertTrue(((String) entry.getKey()).startsWith("isbn"));
      assertTrue(((String) entry.getValue()).contains("Peace"));
    }
  }

  public void testGetCopyCount() {
    gateway.addCopies(new Book("isbn1", "Peace and War 1", "Toy Story"), 2);
    gateway.addCopies(new Book("isbn2", "Peace and War 2", "Toy Story"), 3);
    gateway.addCopies(new Book("isbn3", "Peace and War 3", "Toy Story"), 1);
    assertEquals(8, gateway.copyCount());
  }

  public void testGetAllCDsDoesNotIncludeISBNs() {
    gateway.addCopies(new CompactDisc("abcd", "Peace and War, The Musical", "Gilbertolov and Sullivanalov"), 2);
    gateway.addCopies(new CompactDisc("xyz", "The White Album", "The Beatles"), 2);
    List cds = gateway.findAllCDs();
    assertEquals(2, cds.size());
    assertEquals(6, gateway.copyCount());
  }

  public void testGetAllISBNsDoesNotIncludeCompactDiscs() {
    gateway.addCopies(new CompactDisc("abcd", "Peace and War, The Musical", "Gilbertolov and Sullivanalov"), 2);
    gateway.addCopies(new CompactDisc("xyz", "The White Album", "The Beatles"), 2);
    Map isbnsTitles = gateway.findAllISBNsAndTitles();
    assertEquals(1, isbnsTitles.size());
    assertEquals(6, gateway.copyCount());
  }

  public void testFindAvailableCopyReturnsCopyWhenOneCopyOfManyIsBorrowed()
    throws Exception {
    gateway.addCopies(new Book("isbn1", "Peace and War 1", "Toy Story"), 2);

    List copies = gateway.findAllCopiesByIsbn("isbn1");
    MediaCopy copy = (MediaCopy) copies.get(0);
    copy.setLoaned(new LoanReceipt(new Patron(DateUtil.dateFromString("1/1/2000"))));
    assertNotNull(gateway.findAvailableCopy("isbn1"));
  }

  public void testFindAvailableCopyReturnsNullWhenCopyIsBorrowed() throws Exception {
    gateway.addCopy(new Book("isbn2", "Peace and War", "Toy Story")).getId();
    List copies = gateway.findAllCopiesByIsbn("isbn2");
    MediaCopy copy = (MediaCopy) copies.get(0);
    copy.setLoaned(new LoanReceipt(new Patron(DateUtil.dateFromString("1/1/2000"))));
    assertEquals(null, gateway.findAvailableCopy("isbn2"));
  }

  public void testFindAvailableCopyReturnsCopyWhenCopyIsNotBorrowed() throws Exception {
    assertNotNull(gateway.findAvailableCopy("isbn"));
  }

  public void testFindAvailableCopyShouldReturnNullIfNoSuchBook() throws Exception {
    assertNull(gateway.findAvailableCopy("noSuchBook"));
  }

  public void testContainsShouldReturnFalseIfNoSuchBook() throws Exception {
    assertFalse(gateway.contains("noSuchBook"));
  }

  public void testContainsShouldReturnTrueForCorrectId() throws Exception {
    assertTrue("expected true for recognized isbn", gateway.contains("isbn"));
  }

  public void testMediaCountShouldBeZeroWhenEmpty() {
    gateway.clear();
    assertEquals("expected empty mediaCatalog", 0, gateway.mediaCount());
  }

  public void testCanFindMoreThanOneBook() throws Exception {
    List copies = gateway.findAllCopiesByIsbn("isbn");
    assertEquals(2, copies.size());
    assertTrue(copies.contains(copy1));
    assertTrue(copies.contains(copy2));
  }

  public void testFindCopyShouldReturnCopyForCorrectId() {
    assertNotNull(gateway.findBookCopy("isbn"));
  }

  public void testShouldReturnCorrectBookAmongMany() {
    copy2 = gateway.addCopy(new Book("isbn2", "Peace and War", "Toy Story"));
    assertSame(copy1, gateway.findBookCopy("isbn"));
    assertSame(copy2, gateway.findBookCopy("isbn2"));
  }

  public void testShouldReturnCorrectMediaAmongBooksAndCDs() {
    copy2 = gateway.addCopy(new CompactDisc("111", "title", "artist"));
    assertSame(copy1, gateway.findBookCopy("isbn"));
    assertSame(copy2, gateway.findCdCopy("111"));
  }

  public void testFindCopyShouldReturnNullForUnaddedBooks() {
    assertNull(gateway.findBookCopy("NOT ISBN"));
  }


}
