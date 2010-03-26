package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.CdDoesNotExistException;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.MediaCopy;

public class CompactDiscCatalogTest extends TestCase {

  private MockMediaGateway mockMediaGateway;
  private MockCompactDiscService mockCompactDiscService;
  private CompactDiscCatalog catalog;

  protected void setUp() throws Exception {
    mockCompactDiscService = new MockCompactDiscService();
    mockMediaGateway = new MockMediaGateway();
    catalog = new CompactDiscCatalog(mockCompactDiscService, mockMediaGateway);
  }

  public void testAddBookShouldLookUpCDBarCode() {
    addCDCopy("barCode");
    assertEquals("barCode", mockCompactDiscService.barCodeLastPassedToFind);
  }

  public void testAddBookShouldAddCDReturnedByCDService() {
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
    mockCompactDiscService.setCDToReturn(new CompactDisc(barCode, "Double Rainbow", "Joe Henderson"));
    return catalog.addCDCopy(barCode);
  }

}
