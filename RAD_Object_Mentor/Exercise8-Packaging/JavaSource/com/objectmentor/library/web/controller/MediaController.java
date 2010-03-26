package com.objectmentor.library.web.controller;

import com.objectmentor.library.models.MediaCopy;

import java.util.*;

public abstract class MediaController extends Controller {

  protected abstract ActionResult finishMediaCopiesRequest(ActionResult result);

  protected abstract void handleNewMediaCopiesForNewId(ActionResult result, boolean noBooksOnEntry);

  public ActionResult manage() {
    ActionResult result = new ActionResult();
    if (isPost()) {
      handleDeletedCopies(result);
      handleNewMediaCopiesForExistingId(result);
      handleNewMediaCopiesForNewId(result, getApplication().getMediaGateway().mediaCount() == 0);
    }
    return finishMediaCopiesRequest(result);
  }

  /**
   * A template method, where some of the details are implemented through
   * virtual methods defined by subclasses.
   */
  protected void handleDeletedCopies(ActionResult result) {
    List toDelete = gatherCopiesToDelete();
    deleteCopies(toDelete);
    appendDeletionMessages(result, toDelete);
  }

  private void deleteCopies(List toDelete) {
    for (int i = 0; i < toDelete.size(); i++) {
      MediaCopy mediaCopy = (MediaCopy) toDelete.get(i);
      getApplication().getMediaGateway().delete(mediaCopy);
    }
  }

  private void appendDeletionMessages(ActionResult result, List toDelete) {
    if (toDelete.size() == 0)
      return;
    Collections.sort(toDelete, new MediaCopyIdComparator());
    StringBuffer message = new StringBuffer();
    int count = 1;
    message.append(toDelete.size() == 1 ? "Copy " : "Copies ");
    for (int i = 0; i < toDelete.size(); i++) {
      MediaCopy mediaCopy = (MediaCopy) toDelete.get(i);
      appendDeleteCopyMessage(toDelete.size(), count++, message, mediaCopy);
    }
    message.append("deleted");
    result.appendToInfoMessage(message.toString());
  }

  private List gatherCopiesToDelete() {
    List idsOfCopiesToDelete = getIdsFromMatchingParameters("delete");
    List copiesToDelete = new ArrayList();
    for (int i = 0; i < idsOfCopiesToDelete.size(); i++) {
      String id = (String) idsOfCopiesToDelete.get(i);
      gatherCopyIfExists(id, copiesToDelete);
    }
    return copiesToDelete;
  }

  private void gatherCopyIfExists(String copyId, List toDelete) {
    MediaCopy copy = getApplication().getMediaGateway().findCopyById(copyId);
    if (copy != null)
      toDelete.add(copy);
  }


  private void appendDeleteCopyMessage(int totalNumber, int count,
                                       StringBuffer message, MediaCopy mediaCopy) {
    message.append(mediaCopy.getId());
    if (count != totalNumber)
      message.append(",");
    message.append(" ");
  }

  protected void handleNewMediaCopiesForExistingId(ActionResult result) {
    List isbns = getIdsFromMatchingParameters("newCopies");
    for (Iterator iter = isbns.iterator(); iter.hasNext();) {
      String id = (String) iter.next();
      String newCopyNumber = getParameter("newCopies_" + id);
      if (getApplication().getMediaGateway().contains(id)) {
        if (isValidFormFieldEntry(newCopyNumber)) {
          try {
            int n = Integer.parseInt(newCopyNumber);
            doAccept(result, id, n);
          }
          catch (NumberFormatException e) {
            makeErrorStringForNewCopiesForExistingId(result, id, newCopyNumber);
          }
        }
      }
    }
  }

  protected abstract void doAccept(ActionResult result, String id, int n);

  protected abstract void makeErrorStringForNewCopiesForExistingId(ActionResult result,
                                                                   String id, String newCopyNumber);

  protected boolean acceptFailed(List mediaCopies, int expectedNumberOfNewCopies) {
    return mediaCopies == null
      || mediaCopies.size() != expectedNumberOfNewCopies;
  }

  protected String makeInvalidNumberOfCopiesString() {
    return "You must specify a number of copies greater than or equal to one";
  }

  protected String makePluralNumberString(int size) {
    return Integer.toString(size) + (size > 0 ? " copies" : " copy");
  }

  protected String makeInvalidNumberFormatString() {
    return "You must specify a valid integer for the number of copies (defaults to 1).";
  }


}