package com.objectmentor.library.mocks;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.*;

import java.util.*;

public class MockMediaGateway implements MediaGateway {
  public MediaCopy addedMediaCopy;
  private Map mediaCopies = new HashMap();
  private static long lastId = 0;

  public MediaCopy addCopy(Media media) {
    lastId += 1;
    MediaCopy mediaCopy = new MediaCopy(media, "" + lastId);
    String id = media.getId();
    List copies = (List) mediaCopies.get(id);
    if (copies == null) {
      copies = new LinkedList();
      mediaCopies.put(id, copies);
    }
    copies.add(mediaCopy);
    addedMediaCopy = mediaCopy;
    return mediaCopy;
  }

	public List addCopies(Media book, int numberOfNewBooks) {
		ArrayList list = new ArrayList();
		for (int i=0; i<numberOfNewBooks; i++)
			list.add(addCopy(book));
		return list;
	}

  public MediaCopy findCopy(String isbn) {
    List copies = (List) this.mediaCopies.get(isbn);
    if (copies != null)
      return (MediaCopy) copies.get(0);
    else
      return null;
  }

  public int mediaCount() {
    return mediaCopies.size();
  }

  public List findAllCopies(String isbn) {
    List copies = (List) this.mediaCopies.get(isbn);
    if (copies == null)
      return new ArrayList();
    return copies;
  }

  public boolean contains(String id) {
    return mediaCopies.containsKey(id);
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
    Collection listsOfCopies = mediaCopies.values();
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

	public void delete(String id) {
		mediaCopies.remove(id);
	}

	public List findAllISBNs() {
		return new ArrayList(mediaCopies.keySet());
	}

}
