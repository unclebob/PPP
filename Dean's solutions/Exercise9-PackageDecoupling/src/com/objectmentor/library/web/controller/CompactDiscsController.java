package com.objectmentor.library.web.controller;

import java.util.List;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.models.CompactDisc;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class CompactDiscsController extends MediaController {

  protected ActionResult finishMediaCopiesRequest(ActionResult result) {
    setAttribute("compactDiscs", getApplication().getCompactDiscGateway().findAllCDs());
    render("compactDiscs/manage.jsp");
    return result;
  }

  protected void handleNewMediaCopiesForNewId(ActionResult result, boolean noCopiesOnEntry) {
    String id = getValidatedParameter("id");
    String title = getValidatedParameter("title");
    String artist = getValidatedParameter("artist");
    try {
      int ncopies = getIntegerParameter("newCDsNumberOfCopies", 1);
      if (ncopies <= 0) {
        result.appendToErrorMessage(makeInvalidNumberOfCopiesString());
      } else if (isValidFormFieldEntry(id)) {
        acceptCDs(result, id, title, artist, ncopies);
      } else if (noCopiesOnEntry || isValidFormFieldEntry(title) == false || isValidFormFieldEntry(artist) == false) {
        result.appendToErrorMessage(makeInvalidIdString("Bar Code or other ID"));
      }
    }
    catch (NumberFormatException e) {
      result.appendToErrorMessage(makeInvalidNumberFormatString());
    }
  }
  
  protected String getValidatedParameter(String key) {
    String value = getParameter(key);
    if (isValidFormFieldEntry(key))
      return value;
    else
      return "";
  }

  private void acceptCDs(ActionResult result, String id, String title, String artist, int ncopies) {
    Media media = new CompactDisc(id, title, artist);
    List copies = getApplication().getCompactDiscGateway().addCopies(media, ncopies);
    appendSuccessfullyAddedCDsMessage(result, id, copies);
  }

  private void acceptNewCopiesForExistingCD(ActionResult result, String id, int numberOfNewCDs) {
    try {
      List mediaCopies = getLibrary().acceptCDs(id, numberOfNewCDs);
      if (acceptFailed(mediaCopies, numberOfNewCDs)) {
        handleCDAcceptanceFailed(result, id);
      } else {
        appendSuccessfullyAddedCDsMessage(result, id, mediaCopies);
      }
    }
    catch (IsbnDoesNotExistException e) {
      handleUnknownId(result, "bar code/ID", id);
    }
  }

  private void appendSuccessfullyAddedCDsMessage(ActionResult result, String id, List mediaCopies) {
    MediaCopy copy = (MediaCopy) mediaCopies.get(0);
    CompactDisc disc = (CompactDisc) copy.getMedia();
    StringBuffer buff = new StringBuffer();
    buff.append("Added ")
      .append(makePluralNumberString(mediaCopies.size()))
      .append(" of compact disc \"")
      .append(id)
      .append("\", title = \"")
      .append(disc.getTitle())
      .append("\", artist = \"")
      .append(disc.getAuthor())
      .append("\".");
    result.appendToInfoMessage(buff.toString());
  }

  protected void doAccept(ActionResult result, String id, int n) {
    acceptNewCopiesForExistingCD(result, id, n);
  }

  protected void makeErrorStringForNewCopiesForExistingId(ActionResult result,
                                                          String id, String newCopyNumber) {
    result.appendToErrorMessage("Number of new CDs for bar code \"" + id + "\" must be a valid integer. (It was \"" + newCopyNumber + "\".)");
  }

  private void handleCDAcceptanceFailed(ActionResult result, String id) {
    result.appendToErrorMessage("Adding new CDs with bar code/id \"" + id + "\" failed!");
  }

}
