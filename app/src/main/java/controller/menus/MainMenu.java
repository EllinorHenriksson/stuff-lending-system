package controller.menus;

import model.DayCounter;
import model.Member;
import model.Registry;
import view.Console;
import view.menuChoices.MainChoice;

public class MainMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private Registry registry;
  private DayCounter dayCounter;
  private MemberMenu memberMenu;

  public MainMenu(Registry registry, DayCounter dayCounter) {
    this.registry = registry;
    this.dayCounter = dayCounter;
    this.memberMenu = new MemberMenu(this, registry, dayCounter);
  }

  public void doMainMenu() {
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
    String name = dataFetcher.getName();
    String email = dataFetcher.getEmail();
    String phoneNumber = dataFetcher.getPhoneNumber();

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
      memberMenu.doMemberMenu(member.getId());
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
