package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.Day;
import model.Item;
import model.Member;
import model.Validator;

public class Console {
  private Scanner scan;
  private Validator validator;

  public Console() {
    scan = new Scanner(System.in, "utf-8");
    validator = new Validator();
  }


  public void presentMainMenu() {
    String menu = "\n*** Main Menu ***\n"
        + "simple : List all members\n"
        + "full : List all members (full info)\n"
        + "add : Add new member\n"
        + "select : Select a member\n"
        + "time : Advance time\n"
        + "quit : Quit the program\n";

    System.out.println(menu);
  }

  public void presentMemberMenu() {
    String menu = "\n*** Member Menu ***\n"
        + "delete : Delete member\n"
        + "update : Update member info\n"
        + "info : Show member info\n"
        + "add : Add item to member\n"
        + "main : Back to main menu\n"; 

    System.out.println(menu);
  }

  public void presentItemMenu() {
    String menu = "\n*** Item Menu ***\n"
        + "delete : Delete item\n"
        + "update : Update item info\n"
        + "info : Show item info\n"
        + "contract : Establish contract\n"
        + "member : Back to member menu\n";

    System.out.println(menu);
  }

  public MainChoice getMainChoice() throws Exception {
    System.out.print("Enter menu choice: ");
    String choice = scan.nextLine();

    if (choice.equals("simple")) {
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

  public void presentMessage(String message) {
    System.out.println("\n" + message + "\n");
  }

  public void presentErrorMessage(String message) {
    System.out.println("\nError: " + message + "\n");
  }

  public void presentCurrentDay(Day day) {
    System.out.println("\nCurrent day: " + day.getDayNumber());
  }

  public String getName() {
    System.out.print("\nEnter name: ");
    String name = scan.nextLine();
    validator.validateName(name);
    return name;
  }

  public String getEmail() {
    System.out.print("Enter email: ");
    String email = scan.nextLine();
    validator.validateEmail(email);
    return email;
  }

  public String getPhoneNumber() {
    System.out.print("Enter phone number: ");
    String phoneNumber = scan.nextLine();
    validator.validatePhoneNumber(phoneNumber);
    return phoneNumber;
  }


  public String getMemberId() {
    System.out.print("\nEnter member id: ");
    String id = scan.nextLine();
    validator.validateId(id);
    return id;
  }


  public void presentMembersSimple(ArrayList<Member> members) {
    System.out.println("\n*** Members (simple) ***\n");

    for (Member m : members) {
      System.out.println("ID: " + m.getId());
      System.out.println("Name: " + m.getName());
      System.out.println("Email: " + m.getEmail());
      System.out.println("Phone number: " + m.getPhoneNumber());
      System.out.println("Credits: " + m.getCredits());
      System.out.println("Number of items: " + m.getItems().size());
      System.out.println();
    }
  }

  public void presentMembersFull(ArrayList<Member> members) {
    System.out.println("\n*** Members (full) ***\n");

    for (Member m : members) {
      System.out.println("ID: " + m.getId());
      System.out.println("Name: " + m.getName());
      System.out.println("Email: " + m.getEmail());
      System.out.println("Phone number: " + m.getPhoneNumber());
      System.out.println("Items: ");
      for (Item i : m.getItems()) {
        System.out.println("  Name: " + i.getName());
        System.out.println("  Description: " + i.getDescription());
        System.out.println("  Category: " + i.getType().name());
        System.out.println("  Cost per day: " + i.getCostPerDay() + " credits");
        System.out.println("  Contracts: ");
        for (Contract c : i.getContracts()) {
          System.out.println("    Start day: " + c.getInterval().getStartDay().getDayNumber());
          System.out.println("    End day: " + c.getInterval().getEndDay().getDayNumber());
          System.out.println("    Lender: ");
          System.out.println("      ID: " + c.getLender().getId());
          System.out.println("      Name: " + c.getLender().getName());
          System.out.println("      Email: " + c.getLender().getEmail());
          System.out.println("      Phone number: " + m.getPhoneNumber());
        }
      }
      System.out.println();
    }
  }
}

