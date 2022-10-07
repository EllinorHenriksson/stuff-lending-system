package controller;

import java.util.ArrayList;

import controller.menus.MainMenu;
import model.DayCounter;
import model.Member;
import model.Registry;
import model.persistence.IfPersistence;
import view.Console;

public class User {
  private Registry registry = new Registry();;
  private DayCounter dayCounter = new DayCounter();
  private Console console = new Console();

  private IfPersistence persistence;
  private MainMenu mainMenu;

  public User(IfPersistence persistence) {
    this.persistence = persistence;
    this.mainMenu = new MainMenu(registry, dayCounter);
  }

  public void startProgram() {
    loadMembersToRegistry();
    console.presentCurrentDay(dayCounter.getCurrentDay());
    mainMenu.doMainMenu();
  }

  private void loadMembersToRegistry() {
    ArrayList<Member> members = persistence.loadData();
    for (Member m : members) {
      registry.addMember(m);
    }
  }
}
