package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.CardPrinter;

public class PatronRegistrar {
  protected PatronGateway patronGateway;
  protected CardPrinter cardPrinter;

  public PatronRegistrar(PatronGateway patronGateway, CardPrinter cardPrinter) {
    this.patronGateway = patronGateway;
    this.cardPrinter = cardPrinter;
  }

  public PatronGateway getPatronGateway() {
    return patronGateway;
  }

  public CardPrinter getCardPrinter() {
    return cardPrinter;
  }
}
