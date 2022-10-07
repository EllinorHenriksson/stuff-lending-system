package view;

import java.util.Scanner;

import model.Day;
import model.ItemType;
import model.Validator;

import view.menuChoices.ItemChoice;
import view.menuChoices.MainChoice;
import view.menuChoices.MemberChoice;
import view.menuChoices.UpdateItemChoice;
import view.menuChoices.UpdateMemberChoice;

public class Input {
  private Scanner scan = new Scanner(System.in, "utf-8");
  private Validator validator = new Validator();

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

  public String getLenderId() {
    System.out.print("\nEnter lender ID: ");
    String lenderId = scan.nextLine();
    validator.validateId(lenderId);
    return lenderId;
  }

  public Day getStartDay() {
    System.out.print("\nEnter start day: ");
    int startDay = Integer.parseInt(scan.nextLine());
    return new Day(startDay);
  }

  public Day getEndDay() {
    System.out.print("\nEnter end day: ");
    int endDay = Integer.parseInt(scan.nextLine());
    return new Day(endDay);
  }
}
