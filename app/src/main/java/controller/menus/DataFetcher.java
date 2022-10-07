package controller.menus;

import model.Day;
import model.Interval;
import model.ItemType;
import view.Console;
import view.menuChoices.ItemChoice;
import view.menuChoices.MainChoice;
import view.menuChoices.MemberChoice;
import view.menuChoices.UpdateItemChoice;
import view.menuChoices.UpdateMemberChoice;

public class DataFetcher {
  private Console console = new Console();

  public MainChoice getMainChoice() {
    MainChoice choice = null;
    while (choice == null) {
      console.printMainMenu();
      try {
        choice = console.getMainChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }  
    }

    return choice;
  }

  public MemberChoice getMemberChoice() {
    MemberChoice choice = null;
    while (choice == null) {
      console.printMemberMenu();
      try {
        choice = console.getMemberChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }  
    }

    return choice;
  }

  public UpdateMemberChoice getUpdateMemberChoice() {
    UpdateMemberChoice choice = null;
    while (choice == null) {
      console.printUpdateMemberMenu();
      try {
        choice = console.getUpdateMemberChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }  
    }

    return choice;
  }

  public ItemChoice getItemChoice() {
    ItemChoice choice = null;
    while (choice == null) {
      console.printItemMenu();
      try {
        choice = console.getItemChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }  
    }

    return choice;
  }

  public UpdateItemChoice getUpdateItemChoice() {
    UpdateItemChoice choice = null;
    while (choice == null) {
      console.printUpdateItemMenu();
      try {
        choice = console.getUpdateItemChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }  
    }

    return choice;
  }

  public String getName() {
    String name = null;
    while (name == null) {
      try {
        name = console.getName();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return name;
  }

  public String getEmail() {
    String email = null;
    while (email == null) {
      try {
        email = console.getEmail();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return email;
  }

  public String getPhoneNumber() {
    String phoneNumber = null;
    while (phoneNumber == null) {
      try {
        phoneNumber = console.getPhoneNumber();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return phoneNumber;
  }

  public int getNumberOfDays() {
    int cost = 0;
    while (cost == 0) {
      try {
        cost = console.getNumberOfDays();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return cost;  
  }

  public String getDescription() {
    String description = null;
    while (description == null) {
      try {
        description = console.getDescription();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return description;
  }

  public ItemType getItemType() {
    ItemType type = null;
    while (type == null) {
      try {
        type = console.getType();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return type;
  }

  public int getCostPerDay() {
    int cost = 0;
    while (cost == 0) {
      try {
        cost = console.getCostPerDay();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return cost;  
  }

  public String getMemberId() {
    String id = null;
    while (id == null) {
      try {
        id = console.getMemberId();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return id;
  }

  public String getLenderId() {
    String id = null;
    while (id == null) {
      try {
        id = console.getLenderId();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return id;
  }

  public String getItemId() {
    String id = null;
    while (id == null) {
      try {
        id = console.getItemId();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return id;
  }

  public Interval getInterval() {
    Day startDay = null;
    while (startDay == null) {
      try {
        startDay = console.getStartDay();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    Day endDay = null;
    while (endDay == null) {
      try {
        endDay = console.getEndDay();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
      }
    }

    return new Interval(startDay, endDay);
  }
}
