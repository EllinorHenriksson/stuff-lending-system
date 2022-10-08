package view;

import java.util.ArrayList;

import model.Contract;
import model.Day;
import model.Item;
import model.Member;

/**
 * Class for output object that prints to user.
 */
public class Output {

  /**
   * Prints main menu.
   */
  public void printMainMenu() {
    String menu = "\n*** Main Menu ***\n"
        + "simple : List all members\n"
        + "full : List all members (full info)\n"
        + "add : Add new member\n"
        + "select : Select a member\n"
        + "time : Advance time\n"
        + "quit : Quit the program";

    System.out.println(menu);
  }

  /**
   * Prints member menu.
   */
  public void printMemberMenu() {
    String menu = "\n*** Member Menu ***\n"
        + "delete : Delete member\n"
        + "update : Update member info\n"
        + "info : Show member info\n"
        + "add : Add item to member\n"
        + "select : Select item\n"
        + "main : Back to main menu";

    System.out.println(menu);
  }

  /**
   * Prints the item menu.
   */
  public void printItemMenu() {
    String menu = "\n*** Item Menu ***\n"
        + "delete : Delete item\n"
        + "update : Update item info\n"
        + "info : Show item info\n"
        + "contract : Establish contract\n"
        + "member : Back to member menu";

    System.out.println(menu);
  }

  /**
   * Prints the update member menu.
   */
  public void printUpdateMemberMenu() {
    String menu = "\n*** Update Member Menu ***\n"
        + "name : Update name\n"
        + "email : Update email\n"
        + "phone : Update phone\n"
        + "cancel : Back to member menu";

    System.out.println(menu);
  }

  /**
   * Prints the update item menu.
   */
  public void printUpdateItemMenu() {
    String menu = "\n*** Update Item Menu ***\n"
        + "name : Update name\n"
        + "description : Update description\n"
        + "type : Update type\n"
        + "cost : Update cost per day\n"
        + "cancel : Back to item menu";

    System.out.println(menu);
  }

  /**
   * Prints input message.
   *
   * @param message the message to print.
   */
  public void printMessage(String message) {
    System.out.println("\n" + message);
  }

  /**
   * Prints error message.
   *
   * @param message the error message to print.
   */
  public void printErrorMessage(String message) {
    System.out.println("\nError: " + message);
  }

  /**
   * Prints item info.
   *
   * @param item the item to print.
   */
  public void printItemInfo(Item item) {
    System.out.println("\n  --- " + item.getName() + " ---");
    System.out.println("  Id: " + item.getId());
    System.out.println("  Description: " + item.getDescription());
    System.out.println("  Category: " + item.getType().name().toLowerCase());
    System.out.println("  Cost per day: " + item.getCostPerDay() + " credits");
    System.out.println("  Contracts: ");
    for (Contract c : item.getContracts()) {
      System.out.println("    ---------");
      System.out.println("    Start day: " + c.getInterval().getStartDay().getDayNumber());
      System.out.println("    End day: " + c.getInterval().getEndDay().getDayNumber());
      System.out.println("    Lender: ");
      System.out.println("      ID: " + c.getLender().getId());
      System.out.println("      Name: " + c.getLender().getName());
      System.out.println("      Email: " + c.getLender().getEmail());
      System.out.println("      Phone number: " + c.getLender().getPhoneNumber());
    }
  }

  /**
   * Prints a simple list of all members.
   *
   * @param members the members to print.
   */
  public void printMembersSimple(ArrayList<Member> members) {
    System.out.println("\n*** Members (simple) ***");

    for (Member m : members) {
      System.out.println("\n--- " + m.getName() + " ---");
      System.out.println("ID: " + m.getId());
      System.out.println("Email: " + m.getEmail());
      System.out.println("Phone number: " + m.getPhoneNumber());
      System.out.println("Credits: " + m.getCredits());
      System.out.println("Number of items: " + m.getItems().size());
    }
  }

  /**
   * Prints a full list of all members.
   *
   * @param members the members to print.
   */
  public void printMembersFull(ArrayList<Member> members) {
    System.out.println("\n*** Members (full) ***");
    for (Member m : members) {
      printMemberInfo(m);
    }
  }

  /**
   * Prints info for a single member.
   *
   * @param member the member to print.
   */
  public void printMemberInfo(Member member) {
    System.out.println("\n--- " + member.getName() + " ---");
    System.out.println("ID: " + member.getId());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
    System.out.println("Items: ");
    for (Item i : member.getItems()) {
      printItemInfo(i);
    }
  }

  /**
   * Prints the current day.
   *
   * @param currentDay the day to print.
   */
  public void printCurrentDay(Day currentDay) {
    System.out.println("\nCurrent day: " + currentDay.getDayNumber());
  }
}
