package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import junit.framework.TestCase;

public class CompactDiscCatalogTest extends TestCase {

  private MockMediaGateway mockMediaGateway;
  private CompactDiscCatalog catalog;
  private MockCompactDiscService compactDiscService;

  protected void setUp() throws Exception {
    compactDiscService = new MockCompactDiscService();
    mockMediaGateway = new MockMediaGateway();
    catalog = new CompactDiscCatalog(compactDiscService, mockMediaGateway);
  }

  public void testAddCDCopyShouldAddCDReturnedByCDService() {
    MediaCopy mediaCopy = addCDCopy("barCode");
    assertEquals(mockMediaGateway.addedMediaCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testUnfoundCD() {
    try {
      catalog.addCDCopy("NON-EXISTENT BarCode");
      fail();
    }
    catch (CdDoesNotExistException e) {
    }
  }

  private MediaCopy addCDCopy(String barCode) {
    compactDiscService.setCDToReturn(new CompactDisc(barCode, "Double Rainbow", "Joe Henderson"));
    return catalog.addCDCopy(barCode);
  }

}
