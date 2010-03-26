package com.objectmentor.library.mocks;

import com.objectmentor.library.application.models.Patron;
import com.objectmentor.library.offline.InMemoryCardPrinter;

public class MockCardPrinter extends InMemoryCardPrinter {
  public Patron lastPatronPrinted;

  public void print(Patron patron) {
    lastPatronPrinted = patron;
  }

  public String addressLine() {
    return "Fred F. Flintstone";
  }

}
