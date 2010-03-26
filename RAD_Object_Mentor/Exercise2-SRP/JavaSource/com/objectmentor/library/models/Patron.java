package com.objectmentor.library.models;

public class Patron {

  private String id;
  private final String firstName;
  private final String middleInitial;
  private final String lastName;
  private final String street;
  private final String street2;
  private final String city;
  private final String state;
  private final String zip;

  public Patron(String id, String firstName, String middleInitial,
      String lastName, String street, String street2, String city,
      String state, String zip, String phone) {
    
    this.id = id;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.street = street;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public Patron(String id) {
    this(id, "", "", "", "", "", "", "", "", "");
  }

  public boolean hasId(String expectedId) {
    return id == expectedId;
  }

  public String getId() {
    return id;
  }

  public String getFullName() {
    StringBuffer builder = new StringBuffer(firstName);
    if (middleInitial != null)
      builder.append(" ").append(middleInitial).append(".");
    return builder.append(" ").append(lastName).toString();
  }

  public Object printAddress() {
  	StringBuffer builder = new StringBuffer(street);
    if (street2 != null)
      builder.append("\n").append(street2);
    return builder.append("\n").append(city).append(", ")
    .append(state).append(" ").append(zip).toString();
  }
}
