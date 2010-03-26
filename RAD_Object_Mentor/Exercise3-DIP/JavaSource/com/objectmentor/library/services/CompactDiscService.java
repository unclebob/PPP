package com.objectmentor.library.services;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.models.Media;

public class CompactDiscService {

  Map<String, Media> cds = new HashMap<String, Media>();
  
  //FOR TESTING
	public String barCodeLastPassedToFind;

  public void addCd(Media cd) {
    cds.put(cd.getId(), cd);
  }

  public Media findCDByBarCode(String id) {
    barCodeLastPassedToFind = id;
    return cds.get(id);
  }

  public void clear() {
    cds.clear();
  }
  
  //FOR TESTING
  public void setCDToReturn(Media cd) {
    addCd(cd);
  }



}