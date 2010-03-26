package com.objectmentor.library.application.services;

import com.objectmentor.library.application.models.CompactDisc;

public interface CompactDiscService {
  public CompactDisc findCDByBarCode(String id);

  void clear();
}
