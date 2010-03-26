package com.objectmentor.library.gateways;

import com.objectmentor.library.models.*;

import java.util.*;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfNewBooks);

  MediaCopy findBookCopy(String isbn);

  List findAllCopies(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  List findAllISBNs();

  Map findAllISBNsAndTitles();

  int mediaCount();

  int copyCount();

  List findAllCDs();

  MediaCopy findCdCopy(String barCode);

  List findAllLoanReceiptsFor(String patronId);

  void clear();
}