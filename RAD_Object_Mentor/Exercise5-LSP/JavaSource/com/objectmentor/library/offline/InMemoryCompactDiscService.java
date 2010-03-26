package com.objectmentor.library.offline;

import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.services.CompactDiscService;

import java.util.*;

public class InMemoryCompactDiscService implements CompactDiscService {

  Map<String, CompactDisc> cds = new HashMap<String, CompactDisc>();

  public void addCd(CompactDisc cd) {
    cds.put(cd.getId(), cd);
  }

  public CompactDisc findCDByBarCode(String id) {
    return cds.get(id);
  }

  public void clear() {
    cds.clear();
  }

}