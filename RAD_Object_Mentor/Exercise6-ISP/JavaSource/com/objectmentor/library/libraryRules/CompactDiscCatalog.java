package com.objectmentor.library.libraryRules;

import java.util.List;

import com.objectmentor.library.gateways.CdDoesNotExistException;
import com.objectmentor.library.gateways.CDGateway;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.services.CompactDiscService;

public class CompactDiscCatalog {

  private CompactDiscService compactDiscService;
  private CDGateway cdGateway;

  public CompactDiscCatalog(CompactDiscService compactDiscService, CDGateway cdGateway) {
    this.compactDiscService = compactDiscService;
    this.cdGateway = cdGateway;
  }

  public MediaCopy addCDCopy(String barCode) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    if (cd == null)
      throw new CdDoesNotExistException();
    return cdGateway.addCopy(cd);
  }

  public List addCDCopies(String barCode, int numberOfNewCDs) {
    CompactDisc cd = compactDiscService.findCDByBarCode(barCode);
    return cdGateway.addCopies(cd, numberOfNewCDs);
  }
}
