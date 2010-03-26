package com.objectmentor.library.application.gateways;

import com.objectmentor.library.application.models.Patron;

import java.util.*;

public interface PatronGateway {
  int countActive();

  void add(Patron patron);

  void modify(Patron patron);

  Patron findById(String id);

  Collection findAllPatrons();

  void delete(String id);

  Set findLike(String pattern);

  void clear();
}
