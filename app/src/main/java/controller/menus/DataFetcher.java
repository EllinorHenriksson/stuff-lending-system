package controller.menus;

import model.Day;
import model.Interval;
import model.ItemType;
import view.Console;
import view.menuchoices.ItemChoice;
import view.menuchoices.MainChoice;
import view.menuchoices.MemberChoice;
import view.menuchoices.UpdateItemChoice;
import view.menuchoices.UpdateMemberChoice;

/**
 * Represents a data fetcher that fetches data from the user.
 */
public class DataFetcher {
  private Console console = new Console();

  /**
   * Gets the main menu choice from the user.
   *
   * @return The main menu choice.
   */
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

  /**
   * Gets the member menu choice from the user.
   *
   * @return The member menu choice.
   */
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

  /**
   * Gets the update member menu choice from the user.
   *
   * @return The update member menu choice.
   */
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

  /**
   * Gets the item menu choice from the user.
   *
   * @return The item menu choice.
   */
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

  /**
   * Gets the update item menu choice from the user.
   *
   * @return The update item menu choice.
   */
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

  /**
   * Gets the name (member or item) from the user.
   *
   * @return The name.
   */
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

  /**
   * Gets the member email address from the user.
   *
   * @return The email address.
   */
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

  /**
   * Gets the member phone number from the user.
   *
   * @return The phone number.
   */
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

  /**
   * Gets the number of days from the user.
   *
   * @return The number of days.
   */
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

  /**
   * Gets the item description from the user.
   *
   * @return The item description.
   */
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

  /**
   * Gets the item type from the user.
   *
   * @return The item type.
   */
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

  /**
   * Gets the cost per day from the user.
   *
   * @return The cost per day.
   */
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

  /**
   * Gets the member ID from the user.
   *
   * @return The member ID.
   */
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

  /**
   * Gets the member ID of the lender from the user.
   *
   * @return The member ID of the lender.
   */
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

  /**
   * Gets the item ID from the user.
   *
   * @return The item ID.
   */
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

  /**
   * Gets the interval from the user.
   *
   * @return The interval.
   */
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
