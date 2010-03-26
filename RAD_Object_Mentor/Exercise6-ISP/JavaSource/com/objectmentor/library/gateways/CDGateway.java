package com.objectmentor.library.gateways;

import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.Media;

import java.util.List;

public interface CDGateway {
  List findAllCDs();

  MediaCopy findCdCopy(String barCode);

  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfCopies);
}
