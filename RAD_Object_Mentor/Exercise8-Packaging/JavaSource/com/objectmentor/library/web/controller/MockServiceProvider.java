package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.gateways.CompactDiscGateway;
import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.mocks.MockCardPrinter;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.mocks.MockComputerGateway;
import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.mocks.MockPatronGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;

public class MockServiceProvider implements ServiceProvider {

	private MockMediaGateway mediaGateway;

	public MockServiceProvider() {
		mediaGateway = new MockMediaGateway();
	}
	
  public CardPrinter getCardPrinter() {
    return new MockCardPrinter();
  }

  public CompactDiscService getCompactDiscService() {
    return new MockCompactDiscService();
  }

  public ComputerGateway getComputerGateway() {
    return new MockComputerGateway();
  }

  public IsbnService getIsbnService() {
    MockIsbnService mockIsbnService = new MockIsbnService();
    mockIsbnService.setBookToReturn(new Book("1", "Book 1", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("2", "Book 2", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("3", "Book 3", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("4", "Book 4", "Author 5"));
    mockIsbnService.setBookToReturn(new Book("5", "Book 5", "Author 5"));
    return mockIsbnService;
  }

  public PatronGateway getPatronGateway() {
    return new MockPatronGateway();
  }

  public MediaGateway getMediaGateway() {
  	return mediaGateway;
  }
  
	public BookGateway getBookGateway() {
		return mediaGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
		return mediaGateway;
	}

}
