package com.objectmentor.library.services;

import com.objectmentor.library.models.Patron;

public interface CardPrinter {
  void print(Patron patron);
}