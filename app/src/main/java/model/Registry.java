package model;

import java.util.ArrayList;

public class Registry {
  ArrayList<Member> members;
  IdGenerator idGenerator;
  int idLength = 6;
  
  public Registry() {
    members = new ArrayList<>();
    idGenerator = new IdGenerator(idLength);
  }

  public Member createMember(String name, String email, String phoneNumber, Day currentDay) {
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

    return new Member(name, email, phoneNumber, id, currentDay);
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

  public Member getMember(String id) {
    Member member = getActualMember(id);
    Member copy = new Member(member.getName(), member.getEmail(), member.getPhoneNumber(), member.getId(), member.getCreationDay());
    for (Item i : member.getItems()) {
      copy.addItem(i);
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

  public void updateName(String memberId, String newName) throws Exception {
    Member member = getActualMember(memberId);
    member.setName(newName);
  }

  public void updateEmail(String memberId, String newEmail) throws Exception {
    Member member = getActualMember(memberId);
    if (isEmailUnique(newEmail)) {
      member.setEmail(newEmail);
    } else {
      throw new Exception("Email must be unique.");
    }
  }

  public void updatePhoneNumber(String memberId, String newPhoneNumber) throws Exception {
    Member member = getActualMember(memberId);
    if (isPhoneNumberUnique(newPhoneNumber)) {
      member.setPhoneNumber(newPhoneNumber);
    } else {
      throw new Exception("Phone number must be unique.");
    }
  }

  private Member getActualMember(String id) {
    Member member = null;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getId().equals(id)) {
        member =  members.get(i);
      }
    }
    if (member == null) {
      throw new IllegalArgumentException("Could not find a member with the provided ID.");
    }
    return member;
  }

  public Item getItem(String itemId, String ownerId) {
    return getMember(ownerId).getItem(itemId);
  }

  public Item createItem(String name, String description, Type type, int costPerDay, Day currentDay) {
    String id = "";
    Boolean isUnique = false; 
    while (!isUnique) {
      id = idGenerator.generateId();
      isUnique = isItemIdUnique(id);
    }
    Item item = new Item(name, description, currentDay, costPerDay, type, id);
    return item;
  }

  public void addItemToMember(Item item, String id) throws Exception {
    Member member = getActualMember(id);
    member.addItem(item);
  }
}
