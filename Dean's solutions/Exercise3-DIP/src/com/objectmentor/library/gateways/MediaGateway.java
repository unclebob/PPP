package com.objectmentor.library.gateways;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.LoanReceipt;

public class MediaGateway {

  private Map bookCopies = new HashMap();
  private long lastCopyId = 0;
  
  //FOR TESTING
	public MediaCopy addedBookCopy;

  public MediaGateway() {
    super();
  }

  public MediaCopy addCopy(Media book) {
    lastCopyId += 1;
    MediaCopy bookCopy = new MediaCopy(book, "" + lastCopyId);
    
    //FOR TESTING
  	addedBookCopy = bookCopy;
  	
    String bookId = book.getId();
    List copies = (List) bookCopies.get(bookId);
    if (copies == null) {
      copies = new LinkedList();
      bookCopies.put(bookId, copies);
    }
    copies.add(bookCopy);
    return bookCopy;
  }

  protected String getLastId() {
    return "" + lastCopyId;
  }

  public List addCopies(Media book, int numberOfNewCopies) {
    ArrayList list = new ArrayList();
    for (int i = 0; i < numberOfNewCopies; i++)
      list.add(addCopy(book));
    return list;
  }

  public MediaCopy findBookCopy(String isbn) {
    List copies = (List) this.bookCopies.get(isbn);
    if (copies != null) {
      for (Iterator i = copies.iterator(); i.hasNext();) {
        MediaCopy copy = (MediaCopy) i.next();
        if (copy.isBookCopy())
          return copy;
      }
      return null;
    } else
      return null;
  }

  public MediaCopy findCdCopy(String barCode) {
    List copies = (List) this.bookCopies.get(barCode);
    if (copies != null) {
      for (Iterator i = copies.iterator(); i.hasNext();) {
        MediaCopy copy = (MediaCopy) i.next();
        if (copy.isCdCopy())
          return copy;
      }
      return null;
    } else
      return null;
  }

  public List findAllLoanReceiptsFor(String patronId) {
    List receipts = new LinkedList();
    Collection copyLists = bookCopies.values();
    for (Iterator i = copyLists.iterator(); i.hasNext();) {
      List copyList = (List) i.next();
      for (int j = 0; j < copyList.size(); j++) {
        MediaCopy mediaCopy = (MediaCopy) copyList.get(j);
        LoanReceipt receipt = mediaCopy.getLoanReceipt();
        if (receipt != null && receipt.getBorrower().getId().equals(patronId))
          receipts.add(receipt);
      }
    }
    return receipts;
  }

  public void clear() {
    lastCopyId = 0;
    bookCopies.clear();
  }

  public int mediaCount() {
    return bookCopies.size();
  }

  public List findAllCopies(String isbn) {
    List copies = (List) this.bookCopies.get(isbn);
    if (copies == null)
      return new ArrayList();
    return copies;
  }

  public boolean contains(String id) {
    return bookCopies.containsKey(id);
  }

  public MediaCopy findAvailableCopy(String id) {
    List copies = findAllCopies(id);
    for (int i = 0; i < copies.size(); i++) {
      MediaCopy copy = (MediaCopy) copies.get(i);
      if (!copy.isLoaned())
        return copy;
    }
    return null;
  }

  public MediaCopy findCopyById(String copyId) {
    Collection listsOfCopies = bookCopies.values();
    for (Iterator i = listsOfCopies.iterator(); i.hasNext();) {
      List copies = (List) i.next();
      for (int j = 0; j < copies.size(); j++) {
        MediaCopy mediaCopy = (MediaCopy) copies.get(j);
        if (mediaCopy.getId().equals(copyId))
          return mediaCopy;
      }
    }
    return null;
  }

  public void delete(MediaCopy copy) {
    for (Iterator iter = bookCopies.values().iterator(); iter.hasNext();) {
      List list = (List) iter.next();
      if (list.remove(copy)) {
        return;
      }
    }
  }

  public List findAllISBNs() {
    return findAllKeysForValuesOfType(Media.BOOK);
  }

  public int copyCount() {
    int count = 0;
    for (Iterator iter = bookCopies.values().iterator(); iter.hasNext();) {
      List list = (List) iter.next();
      count += list.size();
    }
    return count;
  }

  public Map findAllISBNsAndTitles() {
    List isbns = findAllKeysForValuesOfType(Media.BOOK);
    Map map = new HashMap();
    for (Iterator iter = isbns.iterator(); iter.hasNext();) {
      String isbn = (String) iter.next();
      List copies = (List) bookCopies.get(isbn);
      if (copies.size() > 0) {
        String title = ((MediaCopy) copies.get(0)).getMedia().getTitle();
        map.put(isbn, title);
      }
    }
    return map;
  }

  public List findAllCDs() {
    return findAllCollectionsOfType(Media.COMPACT_DISC);
  }

  protected List findAllKeysForValuesOfType(int type) {
    List list = new ArrayList();
    for (Iterator iter = bookCopies.keySet().iterator(); iter.hasNext();) {
      String key = (String) iter.next();
      Media media = getFirstInList((List) bookCopies.get(key));
      if (media != null && media.getTypeCode() == type)
        list.add(key);
    }
    return list;
  }

  protected List findAllCollectionsOfType(int type) {
    List list = new ArrayList();
    List keys = findAllKeysForValuesOfType(type);
    for (Iterator iter = keys.iterator(); iter.hasNext();) {
      String key = (String) iter.next();
      list.add(bookCopies.get(key));
    }
    return list;
  }

  protected Media getFirstInList(List list) {
    return list.size() > 0 ? ((MediaCopy) list.get(0)).getMedia() : null;
  }

	public List findAllCopies() {
		List allCopies = new ArrayList();
		for (Iterator i = bookCopies.values().iterator(); i.hasNext();) {
			List copies = (List) i.next();
			allCopies.addAll(copies);
		}
		return allCopies;
	}

}