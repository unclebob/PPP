package com.objectmentor.library.gateways;

import com.objectmentor.library.models.*;

import java.util.*;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List addCopies(Media media, int numberOfCopies);

  MediaCopy findBookCopy(String isbn);

  List findAllCopies();
  
  List findAllCopiesByType(Class type);

  List findAllCopiesByIsbn(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  List findAllISBNs();

  int mediaCount();
  
  int copyCount();
  
  Map findAllISBNsAndTitles();

  List findAllCDs();

  MediaCopy findCdCopy(String barCode);

  List findAllLoanReceiptsFor(String patronId);

  void clear();
}