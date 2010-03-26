package com.objectmentor.library.application;

import com.objectmentor.library.application.gateways.*;
import com.objectmentor.library.application.services.*;

public interface ServiceProvider {

  IsbnService getIsbnService();

  CompactDiscService getCompactDiscService();

  MediaGateway getMediaGateway();
  
  BookGateway getBookGateway();
  
  CompactDiscGateway getCompactDiscGateway();

  PatronGateway getPatronGateway();

  CardPrinter getCardPrinter();

  ComputerGateway getComputerGateway();

}
