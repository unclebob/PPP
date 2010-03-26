package com.objectmentor.library.mocks;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.libraryRules.CompactDiscService;
import com.objectmentor.library.models.Media;

public class MockCompactDiscService implements CompactDiscService {
  public String wasLastCalledWithThisId;
  Map cds = new HashMap();

  public void setCDToReturn(Media cd) {
    cds.put(cd.getId(), cd);
  }

  public Media findCDById(String id) {
    wasLastCalledWithThisId = id;
    return (Media) cds.get(id);
  }
}
