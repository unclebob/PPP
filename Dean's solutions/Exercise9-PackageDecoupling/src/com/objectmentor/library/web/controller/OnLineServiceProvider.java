package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.gateways.CompactDiscGateway;
import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.MockCardPrinter;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.mocks.MockComputerGateway;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.mocks.MockPatronGateway;
import com.objectmentor.library.online.WorldCatIsbnService;
import com.objectmentor.library.services.CardPrinter;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;

/**
 * Provides the real services to the Application. Note that
 * this is using MockServices and MockGateways until such
 * time as the real ones exist.
 */
public class OnLineServiceProvider implements ServiceProvider {
	
	private MockMediaGateway mediaGateway;

	public OnLineServiceProvider() {
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
    return new WorldCatIsbnService();
  }

  public MediaGateway getMediaGateway() {
    return mediaGateway;
  }

  public PatronGateway getPatronGateway() {
    return new MockPatronGateway();
  }

	public BookGateway getBookGateway() {
    return mediaGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
    return mediaGateway;
	}

}
