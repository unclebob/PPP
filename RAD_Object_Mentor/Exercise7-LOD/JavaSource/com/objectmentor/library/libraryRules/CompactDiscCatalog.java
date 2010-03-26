package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.services.*;

import java.util.List;

public class CompactDiscCatalog {

  private CompactDiscService compactDiscService;
  private CompactDiscGateway cdGateway;

  public CompactDiscCatalog(CompactDiscService compactDiscService, CompactDiscGateway cdGateway) {
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
