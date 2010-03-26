package com.objectmentor.library.web.controller;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.web.utils.ControllerTestHelper;
import junit.framework.TestCase;

import javax.servlet.ServletException;
import java.util.List;

public class CompactDiscsController_PostTest extends TestCase {

  private Application application;
  private MockMediaGateway mockMediaGateway;
  private MockCompactDiscService compactDiskService;
  private MockHttpServletRequest request;
  private CompactDiscsController compactDiscController;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    application = helper.getApplication();
    request = helper.createMockRequest("POST");
    request.setAttribute("action_name", "manage");
    mockMediaGateway = (MockMediaGateway) helper.getMediaGateway();
    request.getSession().setAttribute("Application", application);
    compactDiscController = new CompactDiscsController();
    compactDiskService = (MockCompactDiscService) application.getCompactDiscService();
  }

  public void testAddNewCD() throws Exception {
    request.setParameter("id", "barcode");
    request.setParameter("title", "The Wall");
    request.setParameter("artist", "Pink Floyd");

    compactDiscController.handle(request);
    assertEquals(1, mockMediaGateway.mediaCount());

    CompactDisc compactDisc = new CompactDisc("barcode", "The Wall", "Pink Floyd");
    MediaCopy copy = new MediaCopy(compactDisc, "1");
    assertEquals(mockMediaGateway.addedMediaCopy, copy);
    assertNumberOfCDs(1);
  }

  public void testAddNewCDCopiesToExistingCDType() throws Exception {
    CompactDisc cd = new CompactDisc("barcode", "The Wall", "Pink Floyd");
    mockMediaGateway.addCopies(cd, 2);
    assertEquals(1, mockMediaGateway.mediaCount());
    assertEquals(2, mockMediaGateway.copyCount());
    compactDiskService.setCDToReturn(cd);

    request.setParameter("newCopies_barcode", "3");
    compactDiscController.handle(request);
    assertEquals(1, mockMediaGateway.mediaCount());
    assertEquals(5, mockMediaGateway.copyCount());
    assertNumberOfCDs(1);
  }

  private void assertNumberOfCDs(int n) {
    assertEquals(n, ((List) request.getAttribute("compactDiscs")).size());
  }

  public void testDeleteCDs() throws ServletException {
    CompactDisc cd = new CompactDisc("barcode", "The Wall", "Pink Floyd");
    mockMediaGateway.addCopies(cd, 2);
    assertEquals(1, mockMediaGateway.mediaCount());
    assertEquals(2, mockMediaGateway.copyCount());
    request.setParameter("delete_1", "on");
    request.setParameter("delete_2", "on");
    compactDiscController.handle(request);
    assertEquals(1, mockMediaGateway.mediaCount());
    assertEquals(0, mockMediaGateway.copyCount());
    assertNumberOfCDs(0);
  }
}