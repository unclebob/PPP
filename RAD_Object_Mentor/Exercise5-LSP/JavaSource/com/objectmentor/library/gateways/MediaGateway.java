package com.objectmentor.library.gateways;

import com.objectmentor.library.models.*;

import java.util.*;

public interface MediaGateway {
  MediaCopy addCopy(Media media);

  List<MediaCopy> addCopies(Media media, int numberOfCopies);

  MediaCopy findBookCopy(String isbn);

  List findAllCopies();

  List findAllCopies(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  List<String> findAllISBNs();

  Map<String, String> findAllISBNsAndTitles();

  int mediaCount();

  int copyCount();

  List<List> findAllCDs();

  MediaCopy findCdCopy(String barCode);

  List<LoanReceipt> findAllLoanReceiptsFor(String patronId);

  void clear();
}