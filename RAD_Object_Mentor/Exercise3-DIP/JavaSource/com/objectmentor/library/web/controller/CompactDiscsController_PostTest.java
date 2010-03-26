package com.objectmentor.library.web.controller;

import java.util.List;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.web.utils.ControllerTestHelper;

public class CompactDiscsController_PostTest extends TestCase {

  private Application application;
  private MediaGateway mediaGateway;
  private CompactDiscService compactDiscService;
  private MockHttpServletRequest request;
  private CompactDiscsController compactDiscController;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    application = helper.getApplication();
    request = helper.createMockRequest("POST");
    request.setAttribute("action_name", "manage");
    mediaGateway = helper.getMediaGateway();
    request.getSession().setAttribute("Application", application);
    compactDiscController = new CompactDiscsController();
    compactDiscService = application.getCompactDiscService();
  }

  public void testAddNewCD() throws Exception {
    request.setParameter("id", "barcode");
    request.setParameter("title", "The Wall");
    request.setParameter("artist", "Pink Floyd");

    compactDiscController.handle(request);
    assertEquals(1, mediaGateway.mediaCount());

    assertNumberOfCDs(1);
  }

  public void testAddNewCDCopiesToExistingCDType() throws Exception {
  	Media cd = new Media("barcode", "The Wall", "Pink Floyd", Media.COMPACT_DISC);
    mediaGateway.addCopies(cd, 2);
    assertEquals(1, mediaGateway.mediaCount());
    assertEquals(2, mediaGateway.copyCount());
    compactDiscService.setCDToReturn(cd);

    request.setParameter("newCopies_barcode", "3");
    compactDiscController.handle(request);
    assertEquals(1, mediaGateway.mediaCount());
    assertEquals(5, mediaGateway.copyCount());
    assertNumberOfCDs(1);
  }

  private void assertNumberOfCDs(int n) {
    assertEquals(n, ((List<?>) request.getAttribute("compactDiscs")).size());
  }

  public void testDeleteCDs() throws ServletException {
    Media cd = new Media("barcode", "The Wall", "Pink Floyd", Media.COMPACT_DISC);
    mediaGateway.addCopies(cd, 2);
    assertEquals(1, mediaGateway.mediaCount());
    assertEquals(2, mediaGateway.copyCount());
    request.setParameter("delete_1", "on");
    request.setParameter("delete_2", "on");
    compactDiscController.handle(request);
    assertEquals(1, mediaGateway.mediaCount());
    assertEquals(0, mediaGateway.copyCount());
    assertNumberOfCDs(0);
  }
}