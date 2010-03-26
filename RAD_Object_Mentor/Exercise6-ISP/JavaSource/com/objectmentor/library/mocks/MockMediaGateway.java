package com.objectmentor.library.mocks;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.offline.InMemoryMediaGateway;

public class MockMediaGateway extends InMemoryMediaGateway {

  public MediaCopy addedMediaCopy;

  public MediaCopy addCopy(Media media) {
    super.addCopy(media);
    addedMediaCopy = findLastCopyAdded();
    return addedMediaCopy;
  }

  private MediaCopy findLastCopyAdded() {
    return findCopyById(getLastId());
  }
  
}
