package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;

public class PrinterUsageTest extends TestCase {
  private PrintUsage printUsage;
  private Patron patron;

  public void setUp() throws Exception {
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    printUsage = new PrintUsage(patron);
  }

  public void testNothingProcessed() {
    assertEquals(0, printUsage.charges());
  }

  public void testOneRecordProcessed() {
    PrintRecord printRecord = new PrintRecord(3);
    printUsage.pagePrinted(printRecord);
    assertEquals(3 * 5, printUsage.charges());
  }

  public void testAcoupleRecordsProcessed() {
    PrintRecord printRecord = new PrintRecord(3);
    printUsage.pagePrinted(printRecord);
    printUsage.pagePrinted(printRecord);
    assertEquals(6 * 5, printUsage.charges());
  }
}
