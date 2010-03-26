package com.objectmentor.library.gateways;

import com.objectmentor.library.models.*;

import java.util.*;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfCopies);

  List findAllCopies(String mediaId);

  boolean contains(String mediaId);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  int mediaCount();

  int copyCount();

  List findAllLoanReceiptsFor(String patronId);

  void clear();
}