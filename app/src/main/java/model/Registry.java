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

  public Member createMember(String name, String email, String phoneNumber) {
    if (!isEmailUnique(email)) {
      throw new IllegalArgumentException("The email must be unique.");
    }
    if (!isPhoneNumberUnique(phoneNumber)) {
      throw new IllegalArgumentException("The phone number must be unique.");
    }

    String id = "";
    Boolean isUnique = false; 
    while (!isUnique) {
      id = idGenerator.generateId();
      isUnique = isIdUnique(id);
    }

    return new Member(name, email, phoneNumber, id);

  }

  public void addMember(Member m) {
    members.add(m);
  }

  public boolean deleteMember(String id) {
    boolean isDeleted = false;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getId().equals(id)) {
        members.remove(i);
        isDeleted = true;
      }
    }

    return isDeleted;
  }

  public Member getMember(String id) {
    Member member = null;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getId().equals(id)) {
        member =  members.get(i);
      }
    }

    if (member == null) {
      return null;
    } else {
      return new Member(member.getName(), member.getEmail(), member.getPhoneNumber(), member.getId());
    }
  }

  public ArrayList<Member> getMembers() {
    ArrayList<Member> copies = new ArrayList<>();
    for (Member m : members) {
      copies.add(new Member(m.getName(), m.getEmail(), m.getPhoneNumber(), m.getId()));
    }
    return copies;
  }

  private boolean isEmailUnique(String email) {
    Boolean result = true;
    for(Member m : members) {
      if(m.getEmail().equals(email)) {
        result = false;
      }
    }
    return result;
  }

  private boolean isPhoneNumberUnique(String phoneNumber) {
    Boolean result = true;
    for(Member m : members) {
      if(m.getPhoneNumber().equals(phoneNumber)) {
        result = false;
      }
    }
    return result;
  }

  private boolean isIdUnique(String id) {
    Boolean result = true;
    for(Member m : members) {
      if(m.getId().equals(id)) {
        result = false;
      }
    }
    return result;
  }
}
