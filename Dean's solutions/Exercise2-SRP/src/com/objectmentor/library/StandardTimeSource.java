package com.objectmentor.library;

import java.util.Date;

class StandardTimeSource implements TimeSource {
  public Date getTime() {
    return new Date();
  }
}
