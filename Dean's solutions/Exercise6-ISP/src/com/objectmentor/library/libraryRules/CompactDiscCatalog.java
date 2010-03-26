package com.objectmentor.library.libraryRules;

import java.util.List;

import com.objectmentor.library.gateways.CdDoesNotExistException;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.services.CompactDiscService;

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
