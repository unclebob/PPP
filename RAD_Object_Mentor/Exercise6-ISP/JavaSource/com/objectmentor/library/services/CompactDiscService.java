package com.objectmentor.library.services;

import com.objectmentor.library.models.CompactDisc;

public interface CompactDiscService {
  public CompactDisc findCDByBarCode(String id);

  void clear();
}
