package com.objectmentor.library.mocks;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.models.Patron;

import java.util.*;

public class MockPatronGateway implements PatronGateway {
  private static int lastId = 0;

  Map patrons = new HashMap();

  public int countActive() {
    return patrons.size();
  }

  public void add(Patron patron) {
    patron.setId("" + lastId++);
    patrons.put(patron.getId(), patron);
  }

  public void modify(Patron patron) {
    patrons.put(patron.getId(), patron);
  }

  public Patron findById(String id) {
    return (Patron) patrons.get(id);
  }

  static class PatronComparatorById implements Comparator {

		public int compare(Object arg0, Object arg1) {
			Patron p0 = (Patron) arg0;
			Patron p1 = (Patron) arg1;
			return p0.getId().compareTo(p1.getId());
		}
  }

  public Collection findAllPatrons() {
    ArrayList list = new ArrayList(patrons.values());
		Collections.sort(list, new PatronComparatorById());
    return list;
  }

	public void delete(String id) {
		patrons.remove(id);
	}

}
