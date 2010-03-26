package com.objectmentor.library.mocks;

import java.text.ParseException;
import java.util.Date;

import com.objectmentor.library.libraryRules.TimeSource;
import com.objectmentor.library.utils.DateUtil;

public class MockTimeSource extends TimeSource {
  private Date time;

  public MockTimeSource(Date time) {
    this.time = time;
  }

  public MockTimeSource(String dateString) throws ParseException {
    time = DateUtil.dateFromString(dateString);
  }

  public Date getTime() {
    return time;
  }
}
