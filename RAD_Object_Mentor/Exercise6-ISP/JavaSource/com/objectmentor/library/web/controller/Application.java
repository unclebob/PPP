package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.libraryRules.*;
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
  private ComputerCatalog computerCatalog;
	private CompactDiscCatalog cdCatalog;

  public Application(ServiceProvider serviceProvider) {
    isbnService = serviceProvider.getIsbnService();
    compactDiscService = serviceProvider.getCompactDiscService();
    mediaGateway = serviceProvider.getMediaGateway();
    patronGateway = serviceProvider.getPatronGateway();
    cardPrinter = serviceProvider.getCardPrinter();
    computerGateway = serviceProvider.getComputerGateway();
    buildApplication();
  }

  private void buildApplication() {
    mediaGateway.clear();
    patronGateway.getPatronMap().clear();
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

  public BookCatalog getBookCatalog() {
    return bookCatalog;
  }

  public ComputerCatalog getComputerCatalog() {
    return computerCatalog;
  }
}
