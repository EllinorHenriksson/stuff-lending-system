package controller;

import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;

import model.Day;
import model.DayCounter;
import model.Interval;
import model.Item;
import model.Member;
import model.Registry;
import model.ItemType;
import model.persistence.IfPersistence;
import view.Console;
import view.ItemChoice;
import view.MainChoice;
import view.MemberChoice;
import view.UpdateItemChoice;
import view.UpdateMemberChoice;

public class User {
  private Registry registry;
  private DayCounter dayCounter;
  private IfPersistence persistence;
  private Console console;

  public User(IfPersistence persistence) {
    dayCounter = new DayCounter();
    registry = new Registry();
    this.persistence = persistence;
    console = new Console();
  }

  public void startProgram() {
    loadMembersToRegistry();
    console.presentCurrentDay(dayCounter.getCurrentDay());
    doMainMenu();
  }

  private void loadMembersToRegistry() {
    ArrayList<Member> members = persistence.loadData();
    for (Member m : members) {
      registry.addMember(m);
    }
  }

  private void doMainMenu() {
    MainChoice choice = null;
    while (choice == null) {
      console.presentMainMenu();
      try {
        choice = console.getMainChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

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

  private void doMemberMenu(String id) {
    MemberChoice choice = null;
    while (choice == null) {
      console.presentMemberMenu();
      try {
        choice = console.getMemberChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case DELETE: 
        deleteMember(id);
        break;
      case UPDATE:
        doUpdateMemberMenu(id);
        break;
      case INFO:
        showMemberInfo(id);
        break;
      case ADD:
        addItem(id);
        break;
      case SELECT:
        selectItem(id);
        break;
      case MAIN:
        doMainMenu();
        break;
      default:
        break;
    }
  }

  private void doUpdateMemberMenu(String id) {
    UpdateMemberChoice choice = null;
    while (choice == null) {
      console.presentUpdateMemberMenu();
      try {
        choice = console.getUpdateMemberChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case NAME: 
        updateMemberName(id);
        break;
      case EMAIL:
        updateMemberEmail(id);
        break;
      case PHONE:
        updatePhoneNumber(id);
        break;
      case CANCEL:
        doMemberMenu(id);
        break;
      default:
        break;
    }
  }

  private void doUpdateItemMenu(String itemId, String memberId) {
    UpdateItemChoice choice = null;
    while (choice == null) {
      console.presentUpdateItemMenu();
      try {
        choice = console.getUpdateItemChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case NAME: 
        updateItemName(itemId, memberId);
        break;
      case DESCRIPTION:
        updateItemDescription(itemId, memberId);
        break;
      case TYPE:
        updateItemType(itemId, memberId);
        break;
      case COST:
        updateCostPerDay(itemId, memberId);
        break;
      case CANCEL:
        doItemMenu(itemId, memberId);
        break;
      default:
        break;
    }
  }

  public void doItemMenu(String itemId, String memberId) {
    ItemChoice choice = null;
    while (choice == null) {
      console.presentItemMenu();
      try {
        choice = console.getItemChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case DELETE: 
        deleteItem(itemId, memberId);
        break;
      case UPDATE:
        doUpdateItemMenu(itemId, memberId);
        break;
      case INFO:
        showItemInfo(itemId, memberId);
        break;
      case CONTRACT:
        establishContract(itemId, memberId);
        break;
      case MEMBER:
        doMemberMenu(memberId);
        break;
      default:
        break;
    }
  }

  private void showSimpleList() {
    console.presentMembersSimple(registry.getMembers());
    doMainMenu();
  }

  private void showFullList() {
    console.presentMembersFull(registry.getMembers());
    doMainMenu();
  }

  private void addMember() {
    String name = getName();
    String email = getEmail();
    String phoneNumber = getPhoneNumber();

    try {
      Member member = registry.createMember(name, email, phoneNumber, dayCounter.getCurrentDay());
      registry.addMember(member);
      console.presentMessage("Member was successfully added!");
      doMainMenu();
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMainMenu();
    }
  }

  private void addItem(String id) {
    String name = getName();
    String description = getDescription();
    ItemType type = getItemType();
    int costPerDay = getCostPerDay();

    try {
      Item item = registry.createItem(name, description, type, costPerDay, dayCounter.getCurrentDay());
      registry.addItemToMember(item, id);
      console.presentMessage("Item was successfully added!");
      doMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }


  private void establishContract(String itemId, String ownerId) {
    String lenderId = getLenderId();
    Interval interval = getInterval();

    registry.addContractToItem(itemId, interval, lenderId);
    console.presentMessage("Contract was successfully established!");
    doItemMenu(itemId, ownerId);
  }

  private void deleteMember(String id) {
    try {
      registry.removeMember(id);
      console.presentMessage("Member was successfully deleted!");
      doMainMenu();
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private void deleteItem(String itemId, String memberId) {
    try {
      registry.removeItemFromMember(itemId, memberId);
      console.presentMessage("Item was successfully deleted!");
      doMemberMenu(memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }
  }

  private void showMemberInfo(String id) {
    try {
      Member member = registry.getMember(id);
      console.showMemberInfo(member);
      doMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private void showItemInfo(String itemId, String memberId) {
    try {
      Item item = registry.getItem(itemId);
      console.showItemInfo(item);
      doItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }
  }

  private void updateMemberName(String id) {
    String name = getName();
    try {
      registry.updateMemberName(id, name);
      console.presentMessage("Name was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private void updateItemName(String itemId, String memberId) {
    String name = getName();
    try {
      registry.updateItemName(itemId, name);
      console.presentMessage("Name was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }    
  }

  private void updateItemDescription(String itemId, String memberId) {
    String description = getDescription();
    try {
      registry.updateItemDescription(itemId, description);
      console.presentMessage("Description was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }    
  }

  private void updateItemType(String itemId, String memberId) {
    ItemType itemType = getItemType();
    try {
      registry.updateItemType(itemId, itemType);
      console.presentMessage("Type was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }    
  }

  private void updateCostPerDay(String itemId, String memberId) {
    int costPerDay = getCostPerDay();
    try {
      registry.updateItemCostPerDay(itemId, costPerDay);
      console.presentMessage("Cost per day was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }    
  }

  private String getName() {
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

    private String getDescription() {
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

  private ItemType getItemType() {
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

  private int getCostPerDay() {
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

  private String getLenderId() {
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

  private Interval getInterval() {
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

  private void updateMemberEmail(String id) {
    String email = getEmail();
    try {
      registry.updateMemberEmail(id, email);
      console.presentMessage("Email was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private String getEmail() {
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

  private void updatePhoneNumber(String id) {
    String phoneNumber = getPhoneNumber();
    try {
      registry.updateMemberPhoneNumber(id, phoneNumber);
      console.presentMessage("Phone number was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private String getPhoneNumber() {
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

  private void selectMember() {
    String id = null;

    while (id == null) {
      try {
        id = console.getMemberId();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    try {
      Member member = registry.getMember(id);
      doMemberMenu(member.getId());
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMainMenu();
    }
  }

  private void selectItem(String ownerId) {
    String itemId = null;

    while (itemId == null) {
      try {
        itemId = console.getItemId();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }
    }

    try {
      Item item = registry.getItem(itemId);
      doItemMenu(item.getId(), ownerId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doMemberMenu(ownerId);
    }
  }

  private void advanceTime() {
    dayCounter.advanceDay();
    console.presentCurrentDay(dayCounter.getCurrentDay());
    doMainMenu();
  }

  private void quitProgram() {
    System.exit(0);
  }
}
