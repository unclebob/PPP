package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.offline.*;
import com.objectmentor.library.services.*;

/**
 * Construct Application with this ServiceProvider if running
 * the app off-line.
 */
public class OffLineServiceProvider implements ServiceProvider {

  private CardPrinter cardPrinter;
  private CompactDiscService compactDiscService;
  private ComputerGateway computerGateway;
  private IsbnService isbnService;
  private InMemoryMediaGateway mediaGateway;
  private PatronGateway patronGateway;

  public OffLineServiceProvider() {
    cardPrinter = new InMemoryCardPrinter();
    compactDiscService = new InMemoryCompactDiscService();
    computerGateway = new InMemoryComputerGateway();
    isbnService = new InMemoryIsbnService();
    mediaGateway = new InMemoryMediaGateway();
    patronGateway = new InMemoryPatronGateway();
  }

  public CardPrinter getCardPrinter() {
    return cardPrinter;
  }

  public CompactDiscService getCompactDiscService() {
    return compactDiscService;
  }

  public ComputerGateway getComputerGateway() {
    return computerGateway;
  }

  public IsbnService getIsbnService() {
    return isbnService;
  }

  public MediaGateway getMediaGateway() {
    return mediaGateway;
  }

  public PatronGateway getPatronGateway() {
    return patronGateway;
  }

	public BookGateway getBookGateway() {
		return mediaGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
		return mediaGateway;
	}

}
