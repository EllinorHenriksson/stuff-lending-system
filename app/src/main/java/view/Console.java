package view;

import java.util.Scanner;

import model.Day;

public class Console {
  private Scanner scan;

  public Console() {
    scan = new Scanner(System.in);
  }


  public void presentMainMenu() {
    String menu = "*** Main Menu ***\n"
        + "simple : List all members\n"
        + "full : List all members (full info)\n"
        + "add : Add new member\n"
        + "select : Select a member\n"
        + "time : Advance time\n"
        + "quit : Quit the program.";

    System.out.println(menu);
  }

  public void presentMemberMenu() {
    String menu = "*** Member Menu ***\n"
        + "delete : Delete member\n"
        + "update : Update member info\n"
        + "info : Show member info\n"
        + "add : Add item to member\n"
        + "main : Back to main menu."; 

    System.out.println(menu);
  }

  public void presentItemMenu() {
    String menu = "*** Item Menu ***\n"
        + "delete : Delete item\n"
        + "update : Update item info\n"
        + "info : Show item info\n"
        + "contract : Establish contract\n"
        + "member : Back to member menu.";

    System.out.println(menu);
  }

  public MainChoice getMainChoice() throws Exception {
    String choice = scan.nextLine();

    if (choice.equals("list")) {
      return MainChoice.SIMPLE;
    } else if (choice.equals("full")) {
      return MainChoice.FULL;
    } else if (choice.equals("add")) {
      return MainChoice.ADD;
    } else if (choice.equals("select")) {
      return MainChoice.SELECT;
    } else if (choice.equals("time")) {
      return MainChoice.TIME;
    } else if (choice.equals("quit")) {
      return MainChoice.QUIT;
    } else {
      throw new Exception("Not a valid choice.");
    }
  }

  public MemberChoice getMemberChoice() throws Exception {
    String choice = scan.nextLine();

    if (choice.equals("delete")) {
      return MemberChoice.DELETE;
    } else if (choice.equals("update")) {
      return MemberChoice.UPDATE;
    } else if (choice.equals("info")) {
      return MemberChoice.INFO;
    } else if (choice.equals("add")) {
      return MemberChoice.ADD;
    } else if (choice.equals("main")) {
      return MemberChoice.MAIN;
    } else {
      throw new Exception("Not a valid choice.");
    }
  }

  public ItemChoice getItemChoice() throws Exception {
    String choice = scan.nextLine();

    if (choice.equals("delete")) {
      return ItemChoice.DELETE;
    } else if (choice.equals("update")) {
      return ItemChoice.UPDATE;
    } else if (choice.equals("info")) {
      return ItemChoice.INFO;
    } else if (choice.equals("contract")) {
      return ItemChoice.CONTRACT;
    } else if (choice.equals("member")) {
      return ItemChoice.MEMBER;
    } else {
      throw new Exception("Not a valid choice.");
    }
  }

  public void presentErrorMessage(String message) {
    System.out.println("Error: " + message);
  }

  public void presentCurrentDay(Day day) {
    System.out.println("Current day: " + day.getDayNumber() + ".");
  }

  public Member createMember() {
    String name = scan.
  }
}
