package com.objectmentor.library.application;

import com.objectmentor.library.application.gateways.*;
import com.objectmentor.library.application.libraryRules.*;
import com.objectmentor.library.application.services.*;

public class Application {
  private Library library;
  private IsbnService isbnService;
  private MediaGateway mediaGateway;
  private PatronGateway patronGateway;
  private CardPrinter cardPrinter;
  private ComputerGateway computerGateway;
  private CompactDiscService compactDiscService;
  private com.objectmentor.library.application.PatronRegistrar patronRegistrar;
  private com.objectmentor.library.application.BookCatalog bookCatalog;
  private ComputerCatalog computerCatalog;
	private CompactDiscCatalog cdCatalog;
	private BookGateway bookGateway;
	private CompactDiscGateway cdGateway;

  public Application(ServiceProvider serviceProvider) {
    isbnService = serviceProvider.getIsbnService();
    compactDiscService = serviceProvider.getCompactDiscService();
    mediaGateway = serviceProvider.getMediaGateway();
    bookGateway = serviceProvider.getBookGateway();
    cdGateway = serviceProvider.getCompactDiscGateway();
    patronGateway = serviceProvider.getPatronGateway();
    cardPrinter = serviceProvider.getCardPrinter();
    computerGateway = serviceProvider.getComputerGateway();
    buildApplication();
  }

  private void buildApplication() {
    mediaGateway.clear();
    patronGateway.clear();
    computerGateway.clear();

    bookCatalog = new BookCatalog(isbnService, mediaGateway);
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
