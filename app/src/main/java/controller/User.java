package controller;

import controller.menus.MainMenu;
import model.DayCounter;
import model.Registry;
import model.persistence.IfPersistence;
import view.Console;

public class User {
  private Console console = new Console();
  private DayCounter dayCounter = new DayCounter();
  private Registry registry;

  private IfPersistence persistence;
  private MainMenu mainMenu;

  public User(IfPersistence persistence) {
    this.persistence = persistence;
    this.registry = new Registry(dayCounter);
    this.mainMenu = new MainMenu(registry, dayCounter);
  }

  public void startProgram() {
    persistence.loadDataToRegistry(registry);
    console.printCurrentDay(dayCounter.getCurrentDay());
    mainMenu.doMainMenu();
  }
}
