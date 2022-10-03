package model;

import java.util.regex.Pattern;

public class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String id;

  public Member(String name, String email, String phoneNumber, String id) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setId(id);
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must be specified.");
    }

    this.name = name;
  }

  public String getName() {
    return name;
  }


  private void setEmail(String email) {
    if (email == null) {
      throw new IllegalArgumentException("Email must be specified.");
    }

    if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
      throw new IllegalArgumentException("Email must be written in the correct format.");
    }

    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  private void setPhoneNumber(String phoneNumber) {
    if (phoneNumber == null) {
      throw new IllegalArgumentException("Phone number must be specified.");
    }

    if (!Pattern.matches("[0-9]+", phoneNumber)) {
      throw new IllegalArgumentException("Phone number must only consist of numbers.");
    }    
    
    this.phoneNumber = phoneNumber; 
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  private void setId(String id) {
    if (id == null) {
      throw new IllegalArgumentException("ID must be specified.");
    }

    if (id.length() != 6) {
      throw new IllegalArgumentException("ID must be 6 characters long.");
    }
    
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
