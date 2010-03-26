package com.objectmentor.library.offline;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.models.Patron;

import java.util.*;

public class InMemoryPatronGateway implements PatronGateway {

  private static int lastId = 0;
  Map<String, Patron> patrons = new HashMap<String, Patron>();

  protected static class PatronComparatorById implements Comparator {

    public int compare(Object arg0, Object arg1) {
      Patron p0 = (Patron) arg0;
      Patron p1 = (Patron) arg1;
      return p0.getId().compareTo(p1.getId());
    }
  }

  public int getNextId() {
	  return ++lastId;
  }
  
  public Map<String, Patron> getPatronMap() {
	  return patrons;
  }

  public InMemoryPatronGateway() {
    super();
  }

  public List<Patron> getPatronList() {
    ArrayList<Patron> list = new ArrayList<Patron>(patrons.values());
    Collections.sort(list, new PatronComparatorById());
    return list;
  }

}