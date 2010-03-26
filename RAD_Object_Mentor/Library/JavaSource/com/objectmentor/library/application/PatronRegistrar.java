package com.objectmentor.library.application;

import com.objectmentor.library.application.gateways.PatronGateway;
import com.objectmentor.library.application.models.Patron;
import com.objectmentor.library.application.services.CardPrinter;

public class PatronRegistrar {
  private PatronGateway patronGateway;
  private CardPrinter cardPrinter;

  public PatronRegistrar(PatronGateway patronGateway, CardPrinter cardPrinter) {
    this.patronGateway = patronGateway;
    this.cardPrinter = cardPrinter;
  }

  public void registerPatron(Patron patron) {
    patronGateway.add(patron);
    cardPrinter.print(patron);
  }

}
