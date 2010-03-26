package com.objectmentor.library.gateways;

import com.objectmentor.library.models.*;

import java.util.*;

public interface MediaGateway extends BookGateway, CDGateway {

  List findAllCopies(String isbn);

  boolean contains(String id);

  MediaCopy findAvailableCopy(String isbn);

  MediaCopy findCopyById(String copyId);

  void delete(MediaCopy copy);

  int mediaCount();

  int copyCount();

  List findAllLoanReceiptsFor(String patronId);

  void clear();
}