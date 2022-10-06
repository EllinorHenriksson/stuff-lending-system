package controller;

import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import model.DayCounter;
import model.Member;
import model.Registry;
import model.persistence.IfPersistence;
import view.Console;
import view.ItemChoice;
import view.MainChoice;
import view.MemberChoice;
import view.UpdateMemberChoice;

public class User {
  private Registry registry;
  private DayCounter dayCounter;
  private IfPersistence persistence;
  private Console console;

  public User(IfPersistence persistence) {
    dayCounter = new DayCounter();
    registry = new Registry();
    this.persistence = persistence;
    console = new Console();
  }

  public void startProgram() {
    loadMembersToRegistry();
    console.presentCurrentDay(dayCounter.getCurrentDay());
    doMainMenu();
    
  }

  private void loadMembersToRegistry() {
    ArrayList<Member> members = persistence.loadData();
    for (Member m : members) {
      registry.addMember(m);
    }
  }

  private void doMainMenu() {
    MainChoice choice = null;
    while (choice == null) {
      console.presentMainMenu();
      try {
        choice = console.getMainChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case SIMPLE: 
        showSimpleList();
        break;
      case FULL:
        showFullList();
        break;
      case ADD:
        addMember();
        break;
      case SELECT:
        selectMember();
        break;
      case TIME:
        advanceTime();
        break;
      case QUIT:
        quitProgram();
        break;
      default:
        break;
    }
  }

  private void doMemberMenu(String id) {
    MemberChoice choice = null;
    while (choice == null) {
      console.presentMemberMenu();
      try {
        choice = console.getMemberChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case DELETE: 
        deleteMember(id);
        break;
      case UPDATE:
        doUpdateMemberMenu(id);;
        break;
      case INFO:
        // getMemberInfo(id);
        break;
      case ADD:
        // addItem(id);
        break;
      case MAIN:
        doMainMenu();
        break;
      default:
        break;
    }
  }

  private void doUpdateMemberMenu(String id) {
    UpdateMemberChoice choice = null;
    while (choice == null) {
      console.presentUpdateMemberMenu();
      try {
        choice = console.getUpdateMemberChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case NAME: 
        updateMemberName(id);
        break;
      case EMAIL:
        updateMemberEmail(id);
        break;
      case PHONE:
        updatePhoneNumber(id);
        break;
      case CANCEL:
        doMemberMenu(id);
        break;
      default:
        break;
    }
  }

  private void showSimpleList() {
    console.presentMembersSimple(registry.getMembers());
    doMainMenu();
  }

  private void showFullList() {
    console.presentMembersFull(registry.getMembers());
    doMainMenu();
  }

  private void addMember() {
    String name = getMemberName();
    String email = getEmail();
    String phoneNumber = getPhoneNumber();

    try {
      Member member = registry.createMember(name, email, phoneNumber, dayCounter.getCurrentDay());
      registry.addMember(member);
      console.presentMessage("Member was successfully added!");
      doMainMenu();
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMainMenu();
    }
  }

  private void deleteMember(String id) {
    try {
      registry.removeMember(id);
      console.presentMessage("Member was successfully deleted!");
      doMainMenu();
    } catch (Exception e) {
      // console.presentErrorMessage(e.getMessage());
      System.out.println(e);
      doMemberMenu(id);
    }
  }

  private void updateMemberName(String id) {
    String name = getMemberName();
    try {
      registry.updateName(id, name);
      console.presentMessage("Name was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private String getMemberName() {
    String name = null;
    while (name == null) {
      try {
        name = console.getName();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    return name;
  }

  private void updateMemberEmail(String id) {
    String email = getEmail();
    try {
      registry.updateEmail(id, email);
      console.presentMessage("Email was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private String getEmail() {
    String email = null;
    while (email == null) {
      try {
        email = console.getEmail();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    return email;
  }

  private void updatePhoneNumber(String id) {
    String phoneNumber = getPhoneNumber();
    try {
      registry.updatePhoneNumber(id, phoneNumber);
      console.presentMessage("Phone number was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private String getPhoneNumber() {
    String phoneNumber = null;
    while (phoneNumber == null) {
      try {
        phoneNumber = console.getPhoneNumber();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    return phoneNumber;
  }

  private void selectMember() {
    String id = null;

    while (id == null) {
      try {
        id = console.getMemberId();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    try {
      Member member = registry.getMember(id);
      doMemberMenu(member.getId());
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMainMenu();
    }
  }

  private void advanceTime() {
    dayCounter.advanceDay();
    console.presentCurrentDay(dayCounter.getCurrentDay());
    doMainMenu();
  }

  private void quitProgram() {
    System.exit(0);
  }
  
}
