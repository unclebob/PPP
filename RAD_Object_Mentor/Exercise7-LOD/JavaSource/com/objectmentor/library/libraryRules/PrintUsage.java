package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Patron;

public class PrintUsage {
  int totalDue;
  final int perPageRate = 5;

  public PrintUsage(Patron patron) {
    totalDue = 0;
  }

  public int charges() {
    return totalDue;
  }

  public void pagePrinted(PrintRecord printRecord) {
    totalDue += printRecord.pages() * perPageRate;
  }


}
