package com.objectmentor.library.utils;

import java.util.Date;

public abstract class TimeSource {
  public static TimeSource timeSource = new StandardTimeSource();

  public static Date time() {return timeSource.getTime();}

  public abstract Date getTime();
}
