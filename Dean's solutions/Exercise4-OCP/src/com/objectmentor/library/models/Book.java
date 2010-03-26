package com.objectmentor.library.models;

public class Book extends Media {

	public Book(String title, String idOrIssue, int typeCode) {
		super(title, idOrIssue, typeCode);
	}

	public Book(String id, String title, String author) {
		super(id, title, author);
	}
}
