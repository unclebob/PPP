package com.objectmentor.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static int daysLate(Date actualDate, Date dueDate) {
    Calendar cDue = calendarFromDate(dueDate);
    Calendar cActual = calendarFromDate(actualDate);
    long intervalMs = cActual.getTimeInMillis() - cDue.getTimeInMillis();
    return millisecondsToDays(intervalMs);
  }

  private static Calendar calendarFromDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    setTimeToMidnight(calendar);
    return calendar;
  }

  public static int millisecondsToDays(long intervalMs) {
    return (int) (intervalMs / (1000 * 86400));
  }

  public static void setTimeToMidnight(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
  }

  public static Date dateFromString(String dateString) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    df.setLenient(false);
    return df.parse(dateString);
  }

  public static String dateToString(Date date) {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    return df.format(date);
  }
}
