package com.objectmentor.library.libraryRules;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.*;
import com.objectmentor.library.web.controller.MockServiceProvider;
import junit.framework.*;

import java.util.*;

public class LibraryCDTest extends TestCase {
  private MediaCopy copy;
  private Library library;
  private Patron patron;
  private Application application;

  protected void setUp() throws Exception {
    application = new Application(new MockServiceProvider());
    library = application.getLibrary();
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    application.getPatronGateway().add(patron);
    MockCompactDiscService cdService = (MockCompactDiscService) application.getCompactDiscService();
    cdService.setCDToReturn(new CompactDisc("1111", "The Wall", "artist"));
    copy = library.acceptCD("1111");
  }

  public void testAcceptCd() throws Exception {
    MediaCopy foundCopy = application.getCompactDiscGateway().findCopyById(copy.getId());
    assertEquals(copy, foundCopy);
  }

  public void testReturnDateIsOneWeekFromToday() throws Exception {
    Date today = DateUtil.dateFromString("1/1/2000");
    Date due = DateUtil.dateFromString("1/8/2000");
    TimeSource.timeSource = new MockTimeSource(today);
    LoanReceipt r = library.loan(copy, patron);
    assertEquals(due, r.getDueDate());
  }

  public void testFineIfReturnedOneDayLate() throws Exception {
    TimeSource.timeSource = new MockTimeSource("1/1/2000");
    library.loan(copy, patron);
    TimeSource.timeSource = new MockTimeSource("1/9/2000");
    ReturnReceipt rr = library.returnCopy(copy);
    assertEquals(ReturnReceipt.LATE, rr.getStatus());
    Assert.assertEquals(new Money(150), rr.getFines());
  }

  public void testChargeForDamage() throws Exception {
    library.loan(copy, patron);
    List conditions = new ArrayList();
    ReturnCondition damaged = new DamagedCondition();
    conditions.add(damaged);
    ReturnReceipt rr = library.returnCopy(copy, conditions);
    assertEquals(new Money(1500), rr.getCharges().get(damaged.getConditionName()));
  }


}
