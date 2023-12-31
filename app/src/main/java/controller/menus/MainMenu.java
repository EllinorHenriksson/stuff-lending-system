package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.DayCounter;
import model.Member;
import model.Registry;
import view.menuchoices.MainChoice;

/**
 * Represents a main menu.
 */
public class MainMenu extends Menu {
  private DayCounter dayCounter;

  /**
   * Initializing constructor.
   *
   * @param registry The registry to work with.
   * @param dayCounter The day counter keeping track of the current day.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "false positive.")
  public MainMenu(Registry registry, DayCounter dayCounter) {
    super(registry);
    this.dayCounter = dayCounter;
  }

  /**
   * Gets the main menu choice from the user and executes it.
   */
  public void doMenu() {
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

  /**
   * Prints a simple list of the registry's members.
   */
  private void showSimpleList() {
    console.printMembersSimple(registry.getMembers());
    doMenu();
  }

  /**
   * Prints a list of the registry's members with full information.
   */
  private void showFullList() {
    console.printMembersFull(registry.getMembers());
    doMenu();
  }

  /**
   * Gets personal info and adds the member to the registry.
   */
  private void addMember() {
    String name = dataFetcher.getName();
    String email = dataFetcher.getEmail();
    String phoneNumber = dataFetcher.getPhoneNumber();

    try {
      Member member = registry.createMember(name, email, phoneNumber);
      registry.addMember(member);
      console.printMessage("Member was successfully added!");
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }
  
  /**
   * Lets the user select a member to work further with.
   */
  private void selectMember() {
    String memberId = dataFetcher.getMemberId();

    try {
      Member member = registry.getMember(memberId);
      MemberMenu memberMenu =  new MemberMenu(member.getId(), this, registry);
      memberMenu.doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }

  /**
   * Lets the user advance the time.
   */
  private void advanceTime() {
    int numberOfDays = dataFetcher.getNumberOfDays();
    dayCounter.advanceDay(numberOfDays);
    console.printCurrentDay(dayCounter.getCurrentDay());
    doMenu();
  }

  /**
   * Lets the user quit the program.
   */
  @SuppressFBWarnings(value = "DM_EXIT", justification = "false positive.")
  private void quitProgram() {
    System.exit(0);
  }
}
