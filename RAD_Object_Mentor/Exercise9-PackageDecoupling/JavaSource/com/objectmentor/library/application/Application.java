package com.objectmentor.library.application;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.gateways.CompactDiscGateway;
import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.libraryRules.ComputerCatalog;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.services.CardPrinter;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;
import com.objectmentor.library.application.ServiceProvider;

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

  public BookCatalog getBookCatalog() {
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
