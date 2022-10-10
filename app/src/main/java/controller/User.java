package controller;

import controller.menus.MainMenu;
import model.DayCounter;
import model.Registry;
import model.persistence.IfPersistence;
import view.Console;

/**
 * Represents a user controller.
 */
public class User {
  private Console console = new Console();
  private DayCounter dayCounter = new DayCounter();
  private Registry registry;

  private IfPersistence persistence;
  private MainMenu mainMenu;

  /**
   * Initializing constructor.
   *
   * @param persistence A concrete persistence object.
   */
  public User(IfPersistence persistence) {
    this.persistence = persistence;
    this.registry = new Registry(dayCounter);
    this.mainMenu = new MainMenu(registry, dayCounter);
  }

  /**
   * Starts the program by loading data, printing current day and present main menu.
   */
  public void startProgram() {
    persistence.loadDataToRegistry(registry);
    console.printCurrentDay(dayCounter.getCurrentDay());
    mainMenu.doMainMenu();
  }
}
