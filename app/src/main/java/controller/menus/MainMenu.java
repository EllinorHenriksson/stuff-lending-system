package controller.menus;

import model.DayCounter;
import model.Member;
import model.Registry;
import view.Console;
import view.menuchoices.MainChoice;

public class MainMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private Registry registry;
  private DayCounter dayCounter;
  private MemberMenu memberMenu;

  public MainMenu(Registry registry, DayCounter dayCounter) {
    this.registry = registry;
    this.dayCounter = dayCounter;
    this.memberMenu = new MemberMenu(this, registry);
  }

  public void doMainMenu() {
    MainChoice choice = dataFetcher.getMainChoice();

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
    console.printMembersSimple(registry.getMembers());
    doMainMenu();
  }

  private void showFullList() {
    console.printMembersFull(registry.getMembers());
    doMainMenu();
  }

  private void addMember() {
    String name = dataFetcher.getName();
    String email = dataFetcher.getEmail();
    String phoneNumber = dataFetcher.getPhoneNumber();

    try {
      Member member = registry.createMember(name, email, phoneNumber);
      registry.addMember(member);
      console.printMessage("Member was successfully added!");
      doMainMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMainMenu();
    }
  }
  
  private void selectMember() {
    String id = dataFetcher.getMemberId();

    try {
      Member member = registry.getMember(id);
      memberMenu.doMemberMenu(member.getId());
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMainMenu();
    }
  }

  private void advanceTime() {
    int numberOfDays = dataFetcher.getNumberOfDays();
    dayCounter.advanceDay(numberOfDays);
    console.printCurrentDay(dayCounter.getCurrentDay());
    doMainMenu();
  }

  private void quitProgram() {
    System.exit(0);
  }
}
