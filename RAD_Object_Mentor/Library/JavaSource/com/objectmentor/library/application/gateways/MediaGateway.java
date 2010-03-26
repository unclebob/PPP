package com.objectmentor.library.application.gateways;

import com.objectmentor.library.application.models.*;

import java.util.*;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfNewBooks);

  List findAllCopies(String mediaId);

  boolean contains(String mediaId);
  
  void clear();

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  int mediaCount();
  
  int copyCount();
  
  List findAllLoanReceiptsFor(String patronId);
}