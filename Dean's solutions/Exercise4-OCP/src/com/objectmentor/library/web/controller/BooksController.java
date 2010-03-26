package com.objectmentor.library.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.models.MediaCopy;

public class BooksController extends Controller {
	private static final String MANAGEMENT_RESOURCE_PATH = "books/manage.jsp";

  public ActionResult manage() {
  	ActionResult result = new ActionResult();
  	if (isPost()) {
      List isbns = getLibrary().getCatalog().findAllISBNs();
      boolean noBooksOnEntry = isbns.size() == 0;
	  	handleDeletedBookCopies(result, isbns);
	  	handleNewBookCopyForExistingISBN(result);
	  	handleNewBookCopyForNewISBN(result, noBooksOnEntry);
  	}
  	return finishBookCopiesRequest(result);
  }

	private void handleDeletedBookCopies(ActionResult result, List isbns) {
  	// Save the list to delete; we can't delete while iterating!
  	ArrayList toDelete = new ArrayList();
  	for (Iterator iter=isbns.iterator(); iter.hasNext(); ) {
  		String isbn = (String) iter.next();
  		Iterator iter2 = getLibrary().getCatalog().findAllCopies(isbn).iterator();
  		while (iter2.hasNext()) {
  			MediaCopy mediaCopy = (MediaCopy) iter2.next();
  			String id = mediaCopy.getId();
      	String deleteFlag = getParameter("delete_"+id);
      	if (deleteFlag != null && deleteFlag.equals("on")) {
      		toDelete.add(mediaCopy);
      	}
  		}
  		Iterator diter = toDelete.iterator();
  		while (diter.hasNext()) {
  			MediaCopy mediaCopy = (MediaCopy) diter.next();
  			getLibrary().getCatalog().delete(mediaCopy.getId());
  			result.appendToInfoMessage("Copy "+mediaCopy.getId()+" of book \""+mediaCopy.getMedia().getTitle()+"\" deleted.");
  		}
  	}
	}

  private void handleNewBookCopyForExistingISBN(ActionResult result) {
    List isbns = getLibrary().getCatalog().findAllISBNs();
  	for (Iterator iter=isbns.iterator(); iter.hasNext(); ) {
  		String isbn = (String) iter.next();
			String newCopyNumber = getParameter("newCopies_"+isbn);
  		if (newCopyNumber != null && newCopyNumber.length() > 0) {
  			try {
					int n = Integer.parseInt(newCopyNumber);
					acceptBooks(result, isbn, n);
				} catch (NumberFormatException e) {
					result.appendToErrorMessage("Number of new books for ISBN \""+isbn+"\" must be a valid integer. (It was \""+newCopyNumber+"\".)");
				}
  		}
  	}
  }

  private void handleNewBookCopyForNewISBN(ActionResult result, boolean noBooksOnEntry) {
  	String isbn = getParameter("newIsbn");
  	if (isbn != null && isbn.length() > 0) {
  			acceptBooks(result, isbn, 1);
  	} else if (noBooksOnEntry) {
  		result.appendToErrorMessage("You must specify a valid ISBN number");
  	}
  }

	private ActionResult finishBookCopiesRequest(ActionResult result) {
    setIncludePath(MANAGEMENT_RESOURCE_PATH);
    List isbns = getLibrary().getCatalog().findAllISBNs();
    getRequest().setAttribute("isbns", isbns);
    Iterator iter = isbns.iterator();
    while (iter.hasNext()) {
    	String isbn = (String) iter.next();
    	List copies = getLibrary().getCatalog().findAllCopies(isbn);
    	getRequest().setAttribute("copies_"+isbn, copies);
    }
    return result;
	}
  
  protected void acceptBooks(ActionResult result, String isbn, int numberOfNewBooks) {
    if (isNullOrEmpty(isbn)) {
      result.appendToErrorMessage("No ISBN specified!");
      return; 
    }
    try {
      List mediaCopies = getLibrary().acceptBooks(isbn, numberOfNewBooks);
      if (acceptFailed(mediaCopies, numberOfNewBooks)) {
      	result.appendToErrorMessage("Adding new books with ISBN \"" + isbn
	      			+ "\" failed!");
    	} else {
    		appendSuccessfulResult(result, isbn, mediaCopies);
    	}
    } catch (IsbnDoesNotExistException e) {
      result.appendToErrorMessage("The ISBN \"" + isbn + "\" does not exist!");
    }
  }

  private void appendSuccessfulResult(ActionResult result, String isbn, List mediaCopies) {
  	MediaCopy copy = (MediaCopy) mediaCopies.get(0);
    result.appendToInfoMessage("Added copy of book \"" + isbn + "\", title = \""
        + copy.getMedia().getTitle() + ".\" (id = "
        + copy.getId() + ")");
  }

  private boolean acceptFailed(List mediaCopies, int expectedNumberOfNewBooks) {
    return mediaCopies == null || mediaCopies.size() != expectedNumberOfNewBooks;
  }

}
