package com.objectmentor.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
  public static int daysLate(Date actualDate, Date dueDate) {
    return millisecondsToDays(getMilliseconds(actualDate) - getMilliseconds(dueDate));
  }

  private static long getMilliseconds(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTimeInMillis();
  }

  public static int millisecondsToDays(long intervalMs) {
    return (int) (intervalMs / (1000 * 86400));
  }

  public static Date dateFromString(String dateString) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    return dateFormat.parse(dateString);
  }
}
