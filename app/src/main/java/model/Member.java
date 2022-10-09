package model;

import java.util.ArrayList;

/**
 * Class for member object.
 */
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

  /**
   * Constructor for a member object.
   *
   * @param name String.
   * @param email String.
   * @param phoneNumber String.
   * @param id String.
   * @param creationDay Day.
   */
  public Member(String name, String email, String phoneNumber, String id, Day creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setId(id);
    setCreationDay(creationDay);
    items = new ArrayList<>();
    setCredits(0);
  }

  /**
   * Returns a copy of one specified item on a member object.
   *
   * @param itemId String.
   * @return Item.
   */
  public Item getItem(String itemId) {
    Item item = getActualItem(itemId);
    Item copy = new Item(item.getName(), item.getDescription(), item.getType(), item.getCostPerDay(), item.getId(), item.getCreationDay());
    copy.setOwner(item.getOwner());

    for (Contract c : item.getContracts()) {
      copy.addContract(c);
    }
    return item;
  }

  /**
   * Returns the actual reference to a specified item on a member object.
   *
   * @param itemId String.
   * @return item.
   */
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

  /**
   * Returns a copy of the list of items on a member object.
   *
   * @return ArrayList<Item>.
   */
  public ArrayList<Item> getItems() {
    ArrayList<Item> copies = new ArrayList<>();

    for (Item i : items) {
      Item copy = getItem(i.getId());
      copies.add(copy);
    }

    return copies;
  }

  /**
   * Sets the name of a member object.
   *
   * @param name String.
   */
  public void setName(String name) {
    validator.validateName(name);
    this.name = name;
  }

  /**
   * Returns the name of a member object.
   *
   * @return String.
   */
  public String getName() {
    return name;
  }


  /**
   * Sets the email on a member object.
   *
   * @param email String.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns the email on a member object.
   *
   * @return String.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the phone number on a member object.
   *
   * @param phoneNumber String.
   */
  public void setPhoneNumber(String phoneNumber) {
    validator.validatePhoneNumber(phoneNumber);   
    this.phoneNumber = phoneNumber; 
  }

  /**
   * Returns the phone number of a member object.
   *
   * @return String.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the id of a member object.
   *
   * @param id String.
   */
  private void setId(String id) {
    validator.validateId(id);
    this.id = id;
  }

  /**
   * Returns an id of a member object.
   *
   * @return String.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the creation day of a member object.
   *
   * @param creationDay Day.
   */
  private void setCreationDay(Day creationDay) {
    validator.checkNull(creationDay, "Creation day must be specified.");
    this.creationDay = creationDay;
  }

  /**
   * Returns a copy of the creation day of a member object.
   *
   * @return Day.
   */
  public Day getCreationDay() {
    return new Day(creationDay.getDayNumber());
  }

  /**
   * Adds an item to the member object, including adding credits for said item.
   *
   * @param item Item.
   */
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

  /**
   * Adds an item to member object, without transfer of money, used when making copies for printing.
   *
   * @param item Item.
   */
  public void addItemForCopy(Item item) {
    validator.checkNull(item, "Item must be specified.");

    for (Item i : items) {
      if (i == item) {
        throw new IllegalArgumentException("Item already added to member.");
      }
    }

    items.add(item);
  }

  /**
   * Removes an item from a member object.
   *
   * @param id String.
   */
  public void removeItem(String id) {
    Item item = getActualItem(id);

    if (!items.remove(item)) {
      throw new IllegalArgumentException("Item was not found.");
    }

    credits -= 100;
    if (credits < 0) {
      credits = 0;
    }
  }

  /**
   * Sets the credits of a member object.
   */
  public void setCredits(int credits) {
    this.credits = credits;
  }

  /**
   * Returns the credits of a member object.
   *
   * @return int.
   */
  public int getCredits() {
    return credits;
  }

  /**
   * Adds credits to a member object.
   *
   * @param credits int.
   */
  public void addCredits(int credits) {
    validator.checkPositive(credits);
    this.credits += credits;
  }

  /**
   * Removes credits from a member object.
   *
   * @param credits int.
   */
  public void removeCredits(int credits) {
    validator.checkPositive(credits);
    this.credits -= credits;
  }

  /**
   * Updates the item name of an item on a member object.
   *
   * @param itemId String
   * @param newName String.
   */
  public void updateItemName(String itemId, String newName) {
    Item item = getActualItem(itemId);
    item.setName(newName);
  }

  /**
   * Updates the item description of an item on a member object.
   *
   * @param itemId String.
   * @param newDescription String.
   */
  public void updateItemDescription(String itemId, String newDescription) {
    Item item = getActualItem(itemId);
    item.setDescription(newDescription);
  }

  /**
   * Updates the item type of an item on a member object.
   *
   * @param itemId String.
   * @param newType ItemType.
   */
  public void updateItemType(String itemId, ItemType newType) {
    Item item = getActualItem(itemId);
    item.setType(newType);
  }

  /**
   * Updates the cost per day of an item on a member object.
   *
   * @param itemId String.
   * @param newCostPerDay int.
   */
  public void updateItemCostPerDay(String itemId, int newCostPerDay) {
    Item item = getActualItem(itemId);
    item.setCostPerDay(newCostPerDay);
  }

  /**
   * Sets a contract for an item owned by a member object.
   *
   * @param itemId String.
   * @param interval Interval.
   * @param lender member.
   */
  public void establishContractForItem(String itemId, Interval interval, Member lender) {
    Item item = getActualItem(itemId);
    item.establishContract(lender, interval);
  }
}
