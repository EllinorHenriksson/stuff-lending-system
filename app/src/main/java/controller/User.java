package controller;

import java.util.ArrayList;

import model.DayCounter;
import model.Member;
import model.Registry;
import model.persistence.IPersistence;
import view.Console;
import view.*;

public class User {
  private Registry registry;
  private DayCounter dayCounter;
  private IPersistence persistence;
  private Console console;

  public User(IPersistence persistence) {
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
        doSimpleList();
        break;
      case FULL:
        doFullList();
        break;
      case ADD:
        addMember();
        break;
      case SELECT:
        doSelectMember();
        break;
      case TIME:
        advanceTime();
        break;
      case QUIT:
        quitProgram();
        break;
    }
  }

  private void addMember() {
    
  }

  private void advanceTime() {
    dayCounter.advanceDay();
    console.presentCurrentDay(dayCounter.getCurrentDay());
  }

  private void quitProgram() {
    System.exit(0);
  }
  
}
