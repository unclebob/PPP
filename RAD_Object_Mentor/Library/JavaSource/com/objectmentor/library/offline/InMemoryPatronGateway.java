package com.objectmentor.library.offline;

import com.objectmentor.library.application.gateways.PatronGateway;
import com.objectmentor.library.application.models.Patron;

import java.util.*;

public class InMemoryPatronGateway implements PatronGateway {

  private static int lastId = 0;
  private Map patrons = new HashMap();

  static class PatronComparatorById implements Comparator {

    public int compare(Object arg0, Object arg1) {
      Patron p0 = (Patron) arg0;
      Patron p1 = (Patron) arg1;
      return p0.getId().compareTo(p1.getId());
    }
  }

  public int countActive() {
    return patrons.size();
  }

  public void add(Patron patron) {
    patron.setId("" + ++lastId);
    patrons.put(patron.getId(), patron);
  }

  public void modify(Patron patron) {
    patrons.put(patron.getId(), patron);
  }

  public Patron findById(String id) {
    return (Patron) patrons.get(id);
  }

  public Set findLike(String pattern) {
    Set like = new HashSet();
    for (Iterator i = patrons.values().iterator(); i.hasNext();) {
      Patron patron = (Patron) i.next();
      String firstName = patron.getFirstName();
      String lastName = patron.getLastName();
      if (isLike(firstName, pattern) || isLike(lastName, pattern))
        like.add(patron);
    }
    return like;
  }

  public void clear() {
    lastId = 0;
    patrons.clear();
  }

  private boolean isLike(String firstName, String pattern) {
    return firstName.toLowerCase().startsWith(pattern.toLowerCase());
  }

  public InMemoryPatronGateway() {
    super();
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