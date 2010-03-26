package com.objectmentor.library.models;

public class Patron {

	private String id;

	public Patron(String string1, String string2, String string3,
			String string4, Address address, String string5) {
    //string1 is id
    //string2 is first name
    //string3 is middle initial
    //string4 is last name
    //string5 is either phone or zip - can't remember which
		this.id = string1;
	}

	public Patron(String id) {
		this(id, "", "", "", new Address("", "", "", "", ""), "");
	}

	public boolean hasId(String expectedId) {
		return id == expectedId;
	}

	public String getId() {
		return id;
	}
}
