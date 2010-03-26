package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.CdDoesNotExistException;
import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.services.CompactDiscService;

public class MediaCatalogTest extends TestCase {

  private MediaGateway mediaGateway;
  private MediaCatalog catalog;
  private CompactDiscService compactDiscService;

  protected void setUp() throws Exception {
    compactDiscService = new CompactDiscService();
    mediaGateway = new MediaGateway();
    catalog = new MediaCatalog(null, compactDiscService, mediaGateway);
  }

  public void testAddBookShouldLookUpISBN() {
    catalog.addBookCopy("0679600841");
    assertEquals("0679600841", catalog.getIsbnService().isbnLastPassedToFind);
  }

  public void testAddBookShouldLookUpCDBarCode() {
    addCDCopy("barCode");
    assertEquals("barCode", compactDiscService.barCodeLastPassedToFind);
  }

  public void testAddBookShouldAddBookReturnedByIsbnService() {
    MediaCopy mediaCopy = catalog.addBookCopy("1234");
    assertEquals(mediaGateway.addedBookCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testAddBookShouldAddCDReturnedByCDService() {
    MediaCopy mediaCopy = addCDCopy("barCode");
    assertEquals(mediaGateway.addedBookCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testUnfoundISBN() {
    try {
      catalog.addBookCopy("NON-EXISTENT ISBN");
      fail();
    }
    catch (IsbnDoesNotExistException e) {
    }
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
    compactDiscService.setCDToReturn(new Media(barCode, "Double Rainbow", "Joe Henderson", Media.COMPACT_DISC));
    return catalog.addCDCopy(barCode);
  }

}
