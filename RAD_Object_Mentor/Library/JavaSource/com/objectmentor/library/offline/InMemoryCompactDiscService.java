package com.objectmentor.library.offline;

import com.objectmentor.library.application.models.CompactDisc;
import com.objectmentor.library.application.services.CompactDiscService;

import java.util.*;

public class InMemoryCompactDiscService implements CompactDiscService {

  private Map cds = new HashMap();

  public void addCd(CompactDisc cd) {
    cds.put(cd.getId(), cd);
  }

  public CompactDisc findCDByBarCode(String id) {
    return (CompactDisc) cds.get(id);
  }

  public void clear() {
    cds.clear();
  }

}