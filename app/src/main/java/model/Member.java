package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Member {
  static final int creditsForItem = 100;

  private String name;
  private String email;
  private String phoneNumber;
  private String id;
  private Day creationDay;
  private int credits;
  private ArrayList<Item> items;
  private Validator validator = new Validator();

  public Member(String name, String email, String phoneNumber, String id, Day creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setId(id);
    setCreationDay(creationDay);
    items = new ArrayList<>();
    credits = 0;
  }

  public ArrayList<Item> getItems() {
    ArrayList<Item> copies = new ArrayList<>();

    for (Item i : items) {
      Item copy = new Item(i.getName(), i.getDescription(), i.getCreationDay(), i.getCostPerDay(), i.getType());
      for (Contract c : i.getContracts()) {
        copy.addContract(c);
      }
      copies.add(copy);
    }

    return copies;
  }

  public void setName(String name) {
    validator.validateName(name);
    this.name = name;
  }

  public String getName() {
    return name;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPhoneNumber(String phoneNumber) {
    validator.validatePhoneNumber(phoneNumber);   
    this.phoneNumber = phoneNumber; 
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  private void setId(String id) {
    validator.validateId(id);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  private void setCreationDay(Day creationDay) {
    validator.checkNull(creationDay, "Creation day must be specified.");
    this.creationDay = creationDay;
  }

  public Day getCreationDay() {
    return new Day(creationDay.getDayNumber());
  }

  public Item createItem(String name, String description, Day creationDay, int costPerDay, Type type) {
    return new Item(name, description, creationDay, costPerDay, type);
  }

  public void addItem(Item item) {
    validator.checkNull(item, "Item must be specified.");

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
    validator.checkNull(item, "Item must be specified.");

    if (!items.remove(item)) {
      throw new IllegalArgumentException("Item was not found.");
    }
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int credits) {
    validator.validateCredits(credits);
    this.credits += credits;
  }

  public void removeCredits(int credits) {
    validator.validateCredits(credits);
    this.credits -= credits;
  }
}
