package com.objectmentor.library.mocks;

import com.objectmentor.library.models.Patron;

public interface CardPrinter {
	abstract void print(Patron patron);
}