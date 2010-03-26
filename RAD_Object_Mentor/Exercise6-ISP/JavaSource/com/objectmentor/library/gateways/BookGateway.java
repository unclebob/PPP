package com.objectmentor.library.gateways;

import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.Media;

import java.util.List;
import java.util.Map;

public interface BookGateway {
  MediaCopy findBookCopy(String isbn);

  List findAllISBNs();

  Map findAllISBNsAndTitles();

  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfCopies);
}
