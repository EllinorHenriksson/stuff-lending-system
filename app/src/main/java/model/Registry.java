package model;

import java.util.ArrayList;

/**
 * Class for a registry object.
 */
public class Registry {
  int idLength = 6;

  IdGenerator idGenerator;
  ArrayList<Member> members;
  DayCounter dayCounter;
  
  /**
   * Constructor for registry object.
   *
   * @param dayCounter dayCounter.
   */
  public Registry(DayCounter dayCounter) {
    idGenerator = new IdGenerator(idLength);
    members = new ArrayList<>();
    this.dayCounter = dayCounter;
  }

  /**
   * Creates a new member for a registry object.
   *
   * @param name String.
   * @param email String.
   * @param phoneNumber String.
   * @return Member.
   */
  public Member createMember(String name, String email, String phoneNumber) {
    if (!isEmailUnique(email)) {
      throw new IllegalArgumentException("Email must be unique.");
    }
    if (!isPhoneNumberUnique(phoneNumber)) {
      throw new IllegalArgumentException("Phone number must be unique.");
    }

    String id = "";
    Boolean isUnique = false; 
    while (!isUnique) {
      id = idGenerator.generateId();
      isUnique = isMemberIdUnique(id);
    }

    return new Member(name, email, phoneNumber, id, dayCounter.getCurrentDay());
  }

  /**
   * Adds a member to a registry object.
   *
   * @param member Member.
   */
  public void addMember(Member member) {
    for (Member m : members) {
      if (m == member) {
        throw new IllegalArgumentException("Member already added to registry.");
      }
    }
    members.add(member);
  }

  /**
   * Removes a member from a registry object.
   *
   * @param id String.
   * @throws Exception Exception.
   */
  public void removeMember(String id) throws Exception {
    Member memberToRemove = getActualMember(id);
    members.remove(memberToRemove);
  }

  /**
   * Removes an item for a member includen in a registry object.
   *
   * @param itemId String.
   * @param memberId String.
   */
  public void removeItemFromMember(String itemId, String memberId) {
    Member member = getActualMember(memberId);
    member.removeItem(itemId);
  }

  /**
   * Returns a copy of a member included in a registry object.
   *
   * @param id String.
   * @return member.
   */
  public Member getMember(String id) {
    Member member = getActualMember(id);
    Member copy = new Member(member.getName(), member.getEmail(), member.getPhoneNumber(), 
        member.getId(), member.getCreationDay());
    copy.setCredits(member.getCredits());
    for (Item i : member.getItems()) {
      copy.addItemForCopy(i);
    }
    return copy;
  }

  /**
   * Returns a copy of the list of members included in a registry object.
   *
   * @return ArrayList.
   */
  public ArrayList<Member> getMembers() {
    ArrayList<Member> copies = new ArrayList<>();
    for (Member m : members) {
      copies.add(getMember(m.getId()));
    }
    return copies;
  }

  /**
   * Checks if an email is unique.
   *
   * @param email String.
   * @return boolean.
   */
  private boolean isEmailUnique(String email) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getEmail().equals(email)) {
        result = false;
      }
    }
    return result;
  }

  /**
   * Checks if a phone number is unique.
   *
   * @param phoneNumber String.
   * @return boolean.
   */
  private boolean isPhoneNumberUnique(String phoneNumber) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getPhoneNumber().equals(phoneNumber)) {
        result = false;
      }
    }
    return result;
  }

  /**
   * Checks if a member id is unique.
   *
   * @param id String.
   * @return boolean.
   */
  private boolean isMemberIdUnique(String id) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getId().equals(id)) {
        result = false;
      }
    }
    return result;
  }

  /**
   * Checks if an item id is unique.
   *
   * @param id String.
   * @return boolean.
   */
  private boolean isItemIdUnique(String id) {
    Boolean result = true;
    for (Member m : members) {
      for (Item i : m.getItems()) {
        if (i.getId().equals(id)) {
          result = false;
        }
      }
    }
    return result;
  }

  /**
   * Updates the name of a member included in a registry object.
   *
   * @param memberId String.
   * @param newName String.
   * @throws Exception Exception.
   */
  public void updateMemberName(String memberId, String newName) throws Exception {
    Member member = getActualMember(memberId);
    member.setName(newName);
  }

  /**
   * Updates the email of a member included in a registry object.
   *
   * @param memberId String.
   * @param newEmail String.
   * @throws Exception Exception.
   */
  public void updateMemberEmail(String memberId, String newEmail) throws Exception {
    Member member = getActualMember(memberId);
    if (isEmailUnique(newEmail)) {
      member.setEmail(newEmail);
    } else {
      throw new Exception("Email must be unique.");
    }
  }

  /**
   * Updates the phone number of a member included in a registry object.
   *
   * @param memberId String.
   * @param newPhoneNumber String.
   * @throws Exception Exception.
   */
  public void updateMemberPhoneNumber(String memberId, String newPhoneNumber) throws Exception {
    Member member = getActualMember(memberId);
    if (isPhoneNumberUnique(newPhoneNumber)) {
      member.setPhoneNumber(newPhoneNumber);
    } else {
      throw new Exception("Phone number must be unique.");
    }
  }

  /**
   * Calls methods for updating item name on member in a registry object.
   */
  public void updateItemName(String itemId, String newName) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemName(itemId, newName);
  }

  /**
   * Calls methods for updating item description on member in a registry object.
   */
  public void updateItemDescription(String itemId, String newDescription) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemDescription(itemId, newDescription);
  }

  /**
   * Calls methods for updating item type on member in a registry object.
   *
   * @param itemId String.
   * @param newType ItemType.
   */
  public void updateItemType(String itemId, ItemType newType) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemType(itemId, newType);
  }

  /**
   * Calls methods for updating item cost per day on member in a registry object.
   *
   * @param itemId String.
   * @param newCostPerDay int.
   */
  public void updateItemCostPerDay(String itemId, int newCostPerDay) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemCostPerDay(itemId, newCostPerDay);
  }

  /**
   * Returns the actual reference to a member object.
   *
   * @param id String.
   * @return Member.
   */
  private Member getActualMember(String id) {
    Member member = null;

    for (Member m : members) {
      if (m.getId().equals(id)) {
        member = m;
      }
    }

    if (member == null) {
      throw new IllegalArgumentException("Could not find a member with the provided ID.");
    }

    return member;
  }

  /**
   * Calls methods for returning copy of an item for a member in a registry object.
   *
   * @param itemId String.
   * @return Item.
   */
  public Item getItem(String itemId) {
    Item item = null;
    for (Member m : members) {
      for (Item i : m.getItems()) {
        if (i.getId().equals(itemId)) {
          item = i;
        }
      }
    }
    return item;
  }

  /**
   * Creates a new item object.
   */
  public Item createItem(String name, String description, ItemType type, int costPerDay) {
    String id = "";
    Boolean isUnique = false; 
    while (!isUnique) {
      id = idGenerator.generateId();
      isUnique = isItemIdUnique(id);
    }
    Item item = new Item(name, description, type, costPerDay, id, dayCounter.getCurrentDay());
    return item;
  }

  /**
   * Adds an item to a member in a registry object.
   *
   * @param item Item.
   * @param id String.
   * @throws Exception Exception.
   */
  public void addItemToMember(Item item, String id) throws Exception {
    Member member = getActualMember(id);
    member.addItem(item);
  }

  /**
   * Calls methods to set a contract between member objects in a registry object.
   *
   * @param itemId String.
   * @param interval Interval.
   * @param lenderId Member.
   */
  public void establishContractForItem(String itemId, Interval interval, String lenderId) {
    Member owner = findOwnerOfItem(itemId);
    Member lender = getActualMember(lenderId);
    owner.establishContractForItem(itemId, interval, lender);
  }

  /**
   * Returns a copy of an owner of an item within a registry object.
   *
   * @param itemId String.
   * @return Member.
   */
  private Member findOwnerOfItem(String itemId) {
    Member owner = null;
    for (Member m : members) {
      for (Item i : m.getItems()) {
        if (i.getId().equals(itemId)) {
          owner = m;
        }
      }
    }
    return owner;
  }
}
