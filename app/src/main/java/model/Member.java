package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String id;
  private Day creationDay;
  private int credits;
  private final int creditsForItem = 100;
  private ArrayList<Item> items;
  private ArrayList<Contract> contracts;

  public Member(String name, String email, String phoneNumber, String id, Day creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setId(id);
    setCreationDay(creationDay);
    items = new ArrayList<>();
    credits = 0;
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


  public void setEmail(String email) {
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

  public void setPhoneNumber(String phoneNumber) {
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

  private void setCreationDay(Day creationDay) {
    if (creationDay == null) {
      throw new IllegalArgumentException("Creation day must not be null.");
    }

    this.creationDay = creationDay;
  }

  public Day getCreationDay() {
    return new Day(creationDay.getDayNumber());
  }

  public Item createItem(String name, String description, Day creationDay, int costPerDay, Type type) {
    return new Item(name, description, creationDay, costPerDay, type);
  }

  public void addItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item must be specified.");
    }

    for (Item i : items) {
      if (i == item) {
        throw new IllegalArgumentException("Item already added to member.");
      }
    }
    items.add(item);
    addCredits(creditsForItem);

    item.setOwner(this);
  }

  public void removeItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item must be specified.");
    }

    if (!items.remove(item)) {
      throw new IllegalArgumentException("Item was not found.");
    }
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int credits) {
    if (credits < 0) {
      throw new IllegalArgumentException("Credits must be a positive number.");
    }
    this.credits += credits;
  }

  public void removeCredits(int credits) {
    if (credits < 0) {
      throw new IllegalArgumentException("Credits must be a positive number.");
    }
    this.credits -= credits;
  }
}
