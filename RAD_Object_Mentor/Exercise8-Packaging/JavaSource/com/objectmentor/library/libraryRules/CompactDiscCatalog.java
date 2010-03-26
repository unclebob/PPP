package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.services.*;

import java.util.List;

public class CompactDiscCatalog {

  private CompactDiscService compactDiscService;
  private MediaGateway mediaGateway;

  public CompactDiscCatalog(CompactDiscService compactDiscService, MediaGateway mediaGateway) {
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
  }

  public MediaCopy addCDCopy(String barCode) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    if (cd == null)
      throw new CdDoesNotExistException();
    return mediaGateway.addCopy(cd);
  }

  public List addCDCopies(String barCode, int numberOfNewCDs) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    return mediaGateway.addCopies(cd, numberOfNewCDs);
  }
}
