package com.objectmentor.library.mocks;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.libraryRules.CompactDiscService;
import com.objectmentor.library.models.Media;

public class MockCompactDiscService implements CompactDiscService {
  public String wasLastCalledWithThisId;
  Map<String, Media> cds = new HashMap<String, Media>();

  public void setCDToReturn(Media cd) {
    cds.put(cd.getId(), cd);
  }

  public Media findCDById(String id) {
    wasLastCalledWithThisId = id;
    return cds.get(id);
  }
}
