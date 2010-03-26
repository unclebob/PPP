package com.objectmentor.library.application.services;

import com.objectmentor.library.application.models.Patron;

public interface CardPrinter {
  void print(Patron patron);
}