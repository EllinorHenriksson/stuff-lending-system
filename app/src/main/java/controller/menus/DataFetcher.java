package controller.menus;

import model.Day;
import model.Interval;
import model.ItemType;
import view.Console;

public class DataFetcher {
  private Console console = new Console();

  public String getName() {
    String name = null;
    while (name == null) {
      try {
        name = console.getName();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
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
        console.presentErrorMessage(e.getMessage());
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
        console.presentErrorMessage(e.getMessage());
      }
    }

    return phoneNumber;
  }

  public String getDescription() {
    String description = null;
    while (description == null) {
      try {
        description = console.getDescription();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
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
        console.presentErrorMessage(e.getMessage());
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
        console.presentErrorMessage(e.getMessage());
      }
    }

    return cost;  
  }

  public String getLenderId() {
    String lenderId = null;
    while (lenderId == null) {
      try {
        lenderId = console.getLenderId();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    return lenderId;
  }

  public Interval getInterval() {
    Day startDay = null;
    while (startDay == null) {
      try {
        startDay = console.getStartDay();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    Day endDay = null;
    while (endDay == null) {
      try {
        endDay = console.getEndDay();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    return new Interval(startDay, endDay);
  }
}
