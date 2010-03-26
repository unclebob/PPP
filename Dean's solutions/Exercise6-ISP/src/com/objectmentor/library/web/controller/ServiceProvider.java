package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.services.CompactDiscService;
import com.objectmentor.library.services.IsbnService;

public interface ServiceProvider {

  IsbnService getIsbnService();

  CompactDiscService getCompactDiscService();

  MediaGateway getMediaGateway();

  PatronGateway getPatronGateway();

  CardPrinter getCardPrinter();

  ComputerGateway getComputerGateway();

}
