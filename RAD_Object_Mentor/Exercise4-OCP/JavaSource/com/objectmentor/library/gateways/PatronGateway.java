package com.objectmentor.library.gateways;

import com.objectmentor.library.models.Patron;

import java.util.Collection;

public interface PatronGateway {
  int countActive();

  void add(Patron patron);

  void modify(Patron patron);

  Patron findById(String id);

  Collection<Patron> findAllPatrons();

	void delete(String id);
}
