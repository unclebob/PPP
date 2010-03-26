package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.gateways.CompactDiscGateway;
import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.libraryRules.BookCatalog;
import com.objectmentor.library.libraryRules.CompactDiscCatalog;
import com.objectmentor.library.libraryRules.ComputerCatalog;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.libraryRules.PatronRegistrar;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;

public class Application {
  private Library library;
  private IsbnService isbnService;
  private MediaGateway mediaGateway;
  private PatronGateway patronGateway;
  private CardPrinter cardPrinter;
  private ComputerGateway computerGateway;
  private CompactDiscService compactDiscService;
  private PatronRegistrar patronRegistrar;
  private BookCatalog bookCatalog;
  private CompactDiscCatalog cdCatalog;
  private ComputerCatalog computerCatalog;
	private BookGateway bookGateway;
	private CompactDiscGateway cdGateway;

  public Application(ServiceProvider serviceProvider) {
    isbnService = serviceProvider.getIsbnService();
    compactDiscService = serviceProvider.getCompactDiscService();
    patronGateway = serviceProvider.getPatronGateway();
    cardPrinter = serviceProvider.getCardPrinter();
    computerGateway = serviceProvider.getComputerGateway();
    mediaGateway = serviceProvider.getMediaGateway();
    bookGateway = serviceProvider.getBookGateway();
    cdGateway = serviceProvider.getCompactDiscGateway();
    buildApplication();
  }

  private void buildApplication() {
    mediaGateway.clear();
    patronGateway.clear();
    computerGateway.clear();

    bookCatalog = new BookCatalog(isbnService, (BookGateway) mediaGateway);
    cdCatalog = new CompactDiscCatalog(compactDiscService, mediaGateway);
    computerCatalog = new ComputerCatalog(computerGateway);
    library = new Library(bookCatalog, cdCatalog, computerCatalog);
    patronRegistrar = new PatronRegistrar(patronGateway, cardPrinter);
  }

  public IsbnService getIsbnService() {
    return isbnService;
  }

  public Library getLibrary() {
    return library;
  }

  public MediaGateway getMediaGateway() {
    return mediaGateway;
  }

  public PatronGateway getPatronGateway() {
    return patronGateway;
  }

  public CardPrinter getCardPrinter() {
    return cardPrinter;
  }

  public ComputerGateway getComputerGateway() {
    return computerGateway;
  }

  public CompactDiscService getCompactDiscService() {
    return compactDiscService;
  }

  public PatronRegistrar getPatronRegistrar() {
    return patronRegistrar;
  }

  public BookCatalog getMediaCatalog() {
    return bookCatalog;
  }

  public ComputerCatalog getComputerCatalog() {
    return computerCatalog;
  }

	public BookGateway getBookGateway() {
		return bookGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
		return cdGateway;
	}
}
