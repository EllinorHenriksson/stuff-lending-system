package model;

import java.util.ArrayList;

public class Registry {
  int idLength = 6;

  IdGenerator idGenerator;
  ArrayList<Member> members;
  DayCounter dayCounter;
  
  public Registry(DayCounter dayCounter) {
    idGenerator = new IdGenerator(idLength);
    members = new ArrayList<>();
    this.dayCounter = dayCounter;
  }

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

  public void addMember(Member member) {
    for (Member m : members) {
      if (m == member) {
        throw new IllegalArgumentException("Member already added to registry.");
      }
    }
    members.add(member);
  }

  public void removeMember(String id) throws Exception {
    Member memberToRemove = getActualMember(id);
    members.remove(memberToRemove);
  }

  public void removeItemFromMember(String itemId, String memberId) {
    Member member = getActualMember(memberId);
    member.removeItem(itemId);
  }

  public Member getMember(String id) {
    Member member = getActualMember(id);
    Member copy = new Member(member.getName(), member.getEmail(), member.getPhoneNumber(), member.getId(), member.getCreationDay());
    copy.setCredits(member.getCredits());
    for (Item i : member.getItems()) {
      copy.addItemForCopy(i);
    }
    return copy;
  }

  public ArrayList<Member> getMembers() {
    ArrayList<Member> copies = new ArrayList<>();
    for (Member m : members) {
      copies.add(getMember(m.getId()));
    }
    return copies;
  }

  private boolean isEmailUnique(String email) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getEmail().equals(email)) {
        result = false;
      }
    }
    return result;
  }

  private boolean isPhoneNumberUnique(String phoneNumber) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getPhoneNumber().equals(phoneNumber)) {
        result = false;
      }
    }
    return result;
  }

  private boolean isMemberIdUnique(String id) {
    Boolean result = true;
    for (Member m : members) {
      if (m.getId().equals(id)) {
        result = false;
      }
    }
    return result;
  }

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

  public void updateMemberName(String memberId, String newName) throws Exception {
    Member member = getActualMember(memberId);
    member.setName(newName);
  }

  public void updateMemberEmail(String memberId, String newEmail) throws Exception {
    Member member = getActualMember(memberId);
    if (isEmailUnique(newEmail)) {
      member.setEmail(newEmail);
    } else {
      throw new Exception("Email must be unique.");
    }
  }

  public void updateMemberPhoneNumber(String memberId, String newPhoneNumber) throws Exception {
    Member member = getActualMember(memberId);
    if (isPhoneNumberUnique(newPhoneNumber)) {
      member.setPhoneNumber(newPhoneNumber);
    } else {
      throw new Exception("Phone number must be unique.");
    }
  }

  public void updateItemName(String itemId, String newName) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemName(itemId, newName);
  }

  public void updateItemDescription(String itemId, String newDescription) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemDescription(itemId, newDescription);
  }

  public void updateItemType(String itemId, ItemType newType) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemType(itemId, newType);
  }

  public void updateItemCostPerDay(String itemId, int newCostPerDay) {
    Member member = findOwnerOfItem(itemId);
    member.updateItemCostPerDay(itemId, newCostPerDay);
  }

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

  public void addItemToMember(Item item, String id) throws Exception {
    Member member = getActualMember(id);
    member.addItem(item);
  }

  public void establishContractForItem(String itemId, Interval interval, String lenderId) {
    Member owner = findOwnerOfItem(itemId);
    Member lender = getActualMember(lenderId);
    owner.establishContractForItem(itemId, interval, lender);
  }

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
