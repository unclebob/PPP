package com.objectmentor.library.models;

public class CompactDisc extends Media {

	public CompactDisc(String title, String idOrIssue, int typeCode) {
		super(title, idOrIssue, typeCode);
	}

	public CompactDisc(String id, String title, String author) {
		super(id, title, author);
	}
}
