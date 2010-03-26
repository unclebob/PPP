package com.objectmentor.library.libraryRules;

import java.util.Date;

class StandardTimeSource extends TimeSource {
  public Date getTime() {
    return new Date();
  }
}
