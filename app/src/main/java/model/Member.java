package model;

import java.util.ArrayList;

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
  private IdGenerator idGenerator = new IdGenerator(6);

  public Member(String name, String email, String phoneNumber, String id, Day creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setId(id);
    setCreationDay(creationDay);
    items = new ArrayList<>();
    credits = 0;
  }

  public Item getItem(String itemId) {
    Item item = getActualItem(itemId);
    return new Item(item.getName(), item.getDescription(), item.getCreationDay(), item.getCostPerDay(), item.getType(), item.getId());
  }

  private Item getActualItem(String itemId) {
    Item item = null;
    for (Item i : items) {
      if (i.getId().equals(itemId)) {
        item = i;
      }
    }
    if (item == null) {
      throw new IllegalArgumentException("Item does not exist.");
    }
    return item;
  }

  public ArrayList<Item> getItems() {
    ArrayList<Item> copies = new ArrayList<>();

    for (Item i : items) {
      Item copy = new Item(i.getName(), i.getDescription(), i.getCreationDay(), i.getCostPerDay(), i.getType(), i.getId());
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

  public void removeItem(String id) {
    Item item = getActualItem(id);

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

  private boolean isIdUnique(String id) {
    Boolean result = true;
    for (Item item : items) {
      if (item.getId().equals(id)) {
        result = false;
      }
    }
    return result;
  }

  public void updateItemName(String itemId, String newName) {
    Item item = getActualItem(itemId);
    item.setName(newName);
  }

  public void updateItemDescription(String itemId, String newDescription) {
    Item item = getActualItem(itemId);
    item.setDescription(newDescription);
  }

  public void updateItemType(String itemId, ItemType newType) {
    Item item = getActualItem(itemId);
    item.setType(newType);
  }

  public void updateItemCostPerDay(String itemId, int newCostPerDay) {
    Item item = getActualItem(itemId);
    item.setCostPerDay(newCostPerDay);
  }

  public void establishContractForItem(String itemId, Interval interval, Member lender) {
    Item item = getActualItem(itemId);
    item.establishContract(lender, interval);
  }
}
