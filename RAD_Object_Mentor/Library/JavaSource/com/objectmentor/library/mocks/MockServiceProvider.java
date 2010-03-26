package com.objectmentor.library.mocks;

import com.objectmentor.library.application.ServiceProvider;
import com.objectmentor.library.application.gateways.*;
import com.objectmentor.library.application.models.Book;
import com.objectmentor.library.application.services.*;

public class MockServiceProvider implements ServiceProvider {
	
	private MockMediaGateway mockMediaGateway;

	public MockServiceProvider() {
		mockMediaGateway = new MockMediaGateway();
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

  public MediaGateway getMediaGateway() {
		return mockMediaGateway;
  }

  public PatronGateway getPatronGateway() {
    return new MockPatronGateway();
  }

	public BookGateway getBookGateway() {
		return mockMediaGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
		return mockMediaGateway;
	}

}
