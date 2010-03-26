package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.libraryRules.ComputerCatalog;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.libraryRules.MediaCatalog;
import com.objectmentor.library.libraryRules.PatronRegistrar;
import com.objectmentor.library.services.CardPrinter;
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
  private MediaCatalog mediaCatalog;
  private ComputerCatalog computerCatalog;

  public Application() {
    isbnService = new IsbnService();
    compactDiscService = new CompactDiscService();
    cardPrinter = new CardPrinter();
    mediaGateway = new MediaGateway();
    patronGateway = new PatronGateway();
    computerGateway = new ComputerGateway();
    buildApplication();
  }

  private void buildApplication() {
    mediaCatalog = new MediaCatalog(isbnService, compactDiscService, mediaGateway);
    computerCatalog = new ComputerCatalog();
    library = new Library(mediaCatalog, computerCatalog);
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

  public MediaCatalog getMediaCatalog() {
    return mediaCatalog;
  }

  public ComputerCatalog getComputerCatalog() {
    return computerCatalog;
  }
}
