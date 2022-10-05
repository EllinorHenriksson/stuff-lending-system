package controller;

import java.util.ArrayList;
import model.DayCounter;
import model.Member;
import model.Registry;
import model.persistence.IfPersistence;
import view.Console;
import view.ItemChoice;
import view.MainChoice;
import view.MemberChoice;

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
      doMemberMenu(member);
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

  private void doMemberMenu(Member member) {
    // To do
  }

  private void quitProgram() {
    System.exit(0);
  }
  
}
