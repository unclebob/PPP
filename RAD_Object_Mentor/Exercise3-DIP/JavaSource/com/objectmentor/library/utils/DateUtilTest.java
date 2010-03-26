package com.objectmentor.library.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class DateUtilTest extends TestCase {
  public void testDateFromString() throws Exception {
    Date d = DateUtil.dateFromString("1/1/2000");
    Calendar c = new GregorianCalendar(2000, 0, 1, 0, 0, 0);
    assertEquals(d, c.getTime());
  }

  public void testDaysLateWithNoTimes() throws Exception {
    Date due = DateUtil.dateFromString("1/1/2000");
    Date actual = DateUtil.dateFromString("1/2/2000");
    assertEquals(1, DateUtil.daysLate(actual, due));
  }

  public void testDaysLateWhenTwoHoursLateCrossingMidnight() throws Exception {
    Date due = new GregorianCalendar(2000, 0, 1, 23, 0, 0).getTime();
    Date actual = new GregorianCalendar(2000, 0, 2, 1, 0, 0).getTime();
    assertEquals(1, DateUtil.daysLate(actual, due));
  }

  public void testDaysLateWhenOneSecondFromTwoDaysLate() throws Exception {
    Date due = DateUtil.dateFromString("12/5/2006");
    Date actual = new GregorianCalendar(2006, 11, 6, 23, 59, 59).getTime();
    assertEquals(1, DateUtil.daysLate(actual, due));
  }

  public void testDaysLateWhenEarly() throws Exception {
    Date due = DateUtil.dateFromString("1/1/2000");
    Date actual = DateUtil.dateFromString("12/31/1999");
    assertEquals(-1, DateUtil.daysLate(actual, due));
  }


}
