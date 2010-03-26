package com.objectmentor.library.utils;

import java.util.Date;

public class StandardTimeSource extends TimeSource {
  public Date getTime() {
    return new Date();
  }
}
