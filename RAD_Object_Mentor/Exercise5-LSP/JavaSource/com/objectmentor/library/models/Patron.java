package com.objectmentor.library.models;

import java.util.Date;

public class Patron {

  private String  id;
  private String  firstName;
  private String  middleInitial;
  private String  lastName;
  private Date    birthDate;
  private Address address;
  private String  phone;

  public Patron(String firstName, String middleInitial,
                String lastName, Address address, String phone) {
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
  }

  public Patron(Date birthDate) {
    this("", "", "", new Address("", "", "", "", ""), "");
    this.birthDate = birthDate;
  }

  public boolean hasId(String expectedId) {
    return id == expectedId;
  }

  public String getId() {
    return id;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public String getLastName() {
    return lastName;
  }
  
  public String getFullName() {
    StringBuffer buffer = new StringBuffer();
    if (firstName != null && firstName.length() > 0)
      buffer.append(firstName).append(" ");
    if (middleInitial != null && middleInitial.length() > 0)
      buffer.append(middleInitial).append(". ");
    return buffer.append(lastName).toString();
  }

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}


  public void setId(String id) {
    this.id = id;
  }

}
