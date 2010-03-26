package com.objectmentor.library.application;

import java.util.List;

import com.objectmentor.library.application.gateways.CdDoesNotExistException;
import com.objectmentor.library.application.gateways.MediaGateway;
import com.objectmentor.library.application.models.CompactDisc;
import com.objectmentor.library.application.models.MediaCopy;
import com.objectmentor.library.application.services.CompactDiscService;

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
