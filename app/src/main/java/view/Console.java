package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.Day;
import model.Item;
import model.Member;
import model.ItemType;
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
        + "select : Select item\n"
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

  public void presentUpdateMemberMenu() {
    String menu = "\n*** Update Member Menu ***\n"
        + "name : Update name\n"
        + "email : Update email\n"
        + "phone : Update phone\n"
        + "cancel : Back to member menu\n";

    System.out.println(menu);
  }

  public void presentUpdateItemMenu() {
    String menu = "\n*** Update Item Menu ***\n"
    + "name : Update name\n"
    + "desc : Update description\n"
    + "type : Update type\n"
    + "cost : Update cost per day\n"
    + "cancel : Back to item menu\n";

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
    } else if (choice.equals("select")) {
      return MemberChoice.SELECT;
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

  public UpdateMemberChoice getUpdateMemberChoice() throws Exception {
    String choice = scan.nextLine();

    if (choice.equals("name")) {
      return UpdateMemberChoice.NAME;
    } else if (choice.equals("email")) {
      return UpdateMemberChoice.EMAIL;
    } else if (choice.equals("phone")) {
      return UpdateMemberChoice.PHONE;
    } else if (choice.equals("cancel")) {
      return UpdateMemberChoice.CANCEL;
    } else {
      throw new Exception("Not a valid choice.");
    }
  }

  public UpdateItemChoice getUpdateItemChoice() throws Exception {
    String choice = scan.nextLine();

    if (choice.equals("name")) {
      return UpdateItemChoice.NAME;
    } else if (choice.equals("desc")) {
      return UpdateItemChoice.DESCRIPTION;
    } else if (choice.equals("type")) {
      return UpdateItemChoice.TYPE;
    } else if (choice.equals("cost")) {
      return UpdateItemChoice.COST;
    } else if (choice.equals("cancel")) {
      return UpdateItemChoice.CANCEL;
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
    System.out.print("\nEnter email: ");
    String email = scan.nextLine();
    validator.validateEmail(email);
    return email;
  }

  public String getPhoneNumber() {
    System.out.print("\nEnter phone number: ");
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

  public String getItemId() {
    System.out.print("\nEnter item id: ");
    String id = scan.nextLine();
    validator.validateId(id);
    return id;
  }

  public String getDescription() {
    System.out.print("\nEnter description: ");
    String description = scan.nextLine();
    validator.validateDescription(description);
    return description;
  }
  
  public ItemType getType() throws Exception {
    System.out.print("\nEnter type (game, sport, tool, toy, vehicle, other): ");
    String type = scan.nextLine();

    switch (type) {
      case "game":
        return ItemType.GAME;
      case "sport":
        return ItemType.SPORT;
      case "tool":
        return ItemType.TOOL;
      case "toy":
        return ItemType.TOY;
      case "vehicle":
        return ItemType.VEHICLE;
      case "other":
        return ItemType.OTHER;
      default:
        throw new Exception("Invalid type.");
    }
  }

  public int getCostPerDay() throws Exception {
    System.out.print("\nEnter cost per day (credits): ");

    int costPerDay = 0;
    try {
      costPerDay = Integer.parseInt(scan.nextLine());
    } catch (Exception e) {
      throw new Exception("Cost per day must be a number.");
    }

    validator.validateCredits(costPerDay);
    return costPerDay;  
  }

  public void presentMembersSimple(ArrayList<Member> members) {
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

  public void presentMembersFull(ArrayList<Member> members) {
    System.out.println("\n*** Members (full) ***");
    for (Member m : members) {
      showMemberInfo(m);
    }
  }

  public void showMemberInfo(Member member) {
    System.out.println("\n--- " + member.getName() + " ---");
    System.out.println("ID: " + member.getId());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getPhoneNumber());
    System.out.println("Items: ");
    for (Item i : member.getItems()) {
      System.out.println("\n  --- " + i.getName() + " ---");
      System.out.println("  Id: " + i.getId());
      System.out.println("  Description: " + i.getDescription());
      System.out.println("  Category: " + i.getType().name().toLowerCase());
      System.out.println("  Cost per day: " + i.getCostPerDay() + " credits");
      System.out.println("  Contracts: ");
      for (Contract c : i.getContracts()) {
        System.out.println("    Start day: " + c.getInterval().getStartDay().getDayNumber());
        System.out.println("    End day: " + c.getInterval().getEndDay().getDayNumber());
        System.out.println("    Lender: ");
        System.out.println("      ID: " + c.getLender().getId());
        System.out.println("      Name: " + c.getLender().getName());
        System.out.println("      Email: " + c.getLender().getEmail());
        System.out.println("      Phone number: " + c.getLender().getPhoneNumber());
      }
    }
  }
}

