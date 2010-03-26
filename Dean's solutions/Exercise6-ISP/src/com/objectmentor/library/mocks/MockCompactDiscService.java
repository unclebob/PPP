package com.objectmentor.library.mocks;

import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.offline.InMemoryCompactDiscService;

public class MockCompactDiscService extends InMemoryCompactDiscService {
  public String barCodeLastPassedToFind;

  public void setCDToReturn(CompactDisc cd) {
    addCd(cd);
  }

  public CompactDisc findCDByBarCode(String id) {
    barCodeLastPassedToFind = id;
    return super.findCDByBarCode(id);
  }
}
