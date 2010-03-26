package com.objectmentor.library.mocks;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.models.Patron;

import java.util.*;

public class MockPatronGateway implements PatronGateway {
  private static int lastId = 0;

  Map<String, Patron> patrons = new HashMap<String, Patron>();

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
    return patrons.get(id);
  }

  static class PatronComparatorById implements Comparator<Patron> {

		public int compare(Patron p0, Patron p1) {
			return p0.getId().compareTo(p1.getId());
		}
  }

  public Collection<Patron> findAllPatrons() {
    ArrayList<Patron> list = new ArrayList<Patron>(patrons.values());
		Collections.sort(list, new PatronComparatorById());
    return list;
  }

	public void delete(String id) {
		patrons.remove(id);
	}

}
