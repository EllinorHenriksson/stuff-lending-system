package controller;

import java.util.ArrayList;
import java.util.Scanner;

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
    persistence.loadDataToRegistry(registry);
    console.printCurrentDay(dayCounter.getCurrentDay());
    mainMenu.doMainMenu();
  }
}
