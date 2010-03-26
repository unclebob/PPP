package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.libraryRules.CompactDiscService;
import com.objectmentor.library.libraryRules.IsbnService;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.mocks.MockCardPrinter;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.mocks.MockComputerGateway;
import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.mocks.MockPatronGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.services.WorldCatIsbnService;

public class Application {
  private Library library;
  private IsbnService isbnService;
  private MediaGateway mediaGateway;
  private PatronGateway patronGateway;
  private CardPrinter cardPrinter;
  private ComputerGateway computerGateway;
  private CompactDiscService compactDiscService;

  public Application(
  		IsbnService        isbnService, 
  		CompactDiscService compactDiscService, 
  		MediaGateway       mediaGateway, 
  		PatronGateway      patronGateway, 
  		CardPrinter        cardPrinter, 
  		ComputerGateway    computerGateway) {
    this.isbnService        = isbnService;
    this.compactDiscService = compactDiscService;
    this.mediaGateway       = mediaGateway;
    this.patronGateway      = patronGateway;
    this.cardPrinter        = cardPrinter;
    this.computerGateway    = computerGateway;
  }

  public Application(boolean real) {
    // TODO you would normally put in real objects or calls to factories of some kind.
    this.isbnService = real ? ((IsbnService) new WorldCatIsbnService()) : (initIsbnService());
    this.compactDiscService = new MockCompactDiscService();
    this.mediaGateway       = new MockMediaGateway();
    this.patronGateway      = new MockPatronGateway();
    this.cardPrinter        = new MockCardPrinter();
    this.computerGateway    = new MockComputerGateway();
  }

  public Application() {
    this(false);
  }

  private MockIsbnService initIsbnService() {
    MockIsbnService mockIsbnService = new MockIsbnService();
    mockIsbnService.setBookToReturn(new Book("1", "Book 1", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("2", "Book 2", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("3", "Book 3", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("4", "Book 4", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("5", "Book 5", "Author 5"));
    return mockIsbnService;
  }

  public Library getLibrary() {
    if (library == null) {
      library = new Library(isbnService, compactDiscService, mediaGateway, patronGateway, cardPrinter, computerGateway);
    }
    return library;
  }

  public MediaGateway getMediaGateway() {
    return mediaGateway;
  }
}
