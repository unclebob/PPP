package com.objectmentor.library.online;

import com.objectmentor.library.application.ServiceProvider;
import com.objectmentor.library.application.gateways.*;
import com.objectmentor.library.application.services.*;
import com.objectmentor.library.offline.*;

/**
 * Provides the real services to the Application. Note that
 * this is using MockServices and MockGateways until such
 * time as the real ones exist.
 */
public class OnLineServiceProvider implements ServiceProvider {
	
	private InMemoryMediaGateway inMemoryMediaGateway;

	public OnLineServiceProvider() {
		inMemoryMediaGateway = new InMemoryMediaGateway();
	}

  public CardPrinter getCardPrinter() {
    return new InMemoryCardPrinter();
  }

  public CompactDiscService getCompactDiscService() {
    return new InMemoryCompactDiscService();
  }

  public ComputerGateway getComputerGateway() {
    return new InMemoryComputerGateway();
  }

  public IsbnService getIsbnService() {
    return new WorldCatIsbnService();
  }

  public PatronGateway getPatronGateway() {
    return new InMemoryPatronGateway();
  }
  
  public MediaGateway getMediaGateway() {
		return inMemoryMediaGateway;
  }

	public BookGateway getBookGateway() {
		return inMemoryMediaGateway;
	}

	public CompactDiscGateway getCompactDiscGateway() {
		return inMemoryMediaGateway;
	}

}
