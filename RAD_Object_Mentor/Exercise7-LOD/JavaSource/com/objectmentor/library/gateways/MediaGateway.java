package com.objectmentor.library.gateways;

import java.util.List;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfNewBooks);

  List findAllCopies(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  int mediaCount();
  
  List findAllLoanReceiptsFor(String patronId);
  
  void clear();

  int copyCount();
}