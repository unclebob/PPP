package com.objectmentor.library.mocks;

import com.objectmentor.library.models.Patron;

public class MockCardPrinter implements CardPrinter {
	public Patron lastPatronPrinted;

	public void print(Patron patron) {
		lastPatronPrinted = patron;
	}

	public String addressLine() {
		return "Fred F. Flintstone";
	}

}
