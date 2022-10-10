package controller.menus;

import model.Item;
import model.ItemType;
import model.Member;
import model.Registry;
import view.Console;
import view.menuchoices.MemberChoice;

/**
 * Represents a member menu.
 */
public class MemberMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private String memberId;

  private MainMenu mainMenu;

  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param memberId The ID of the current member.
   * @param mainMenu The main menu to work with.
   * @param registry The registry to work with.
   */
  public MemberMenu(String memberId, MainMenu mainMenu, Registry registry) {
    this.memberId = memberId;
    this.mainMenu = mainMenu;
    this.registry = registry;
  }

  /**
   * Gets the member menu choice from the user and executes it.
   */
  public void doMemberMenu() {
    MemberChoice choice = dataFetcher.getMemberChoice();

    switch (choice) {
      case DELETE: 
        deleteMember();
        break;
      case UPDATE:
        updateMember();
        break;
      case INFO:
        showMemberInfo();
        break;
      case ADD:
        addItem();
        break;
      case SELECT:
        selectItem();
        break;
      case MAIN:
        mainMenu.doMainMenu();
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user delete a member.
   */
  private void deleteMember() {
    try {
      registry.removeMember(memberId);
      console.printMessage("Member was successfully deleted!");
      mainMenu.doMainMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu();
    }
  }

  private void updateMember() {
    UpdateMemberMenu updateMemberMenu = new UpdateMemberMenu(memberId, this, registry);
    updateMemberMenu.doUpdateMemberMenu();
  }

  /**
   * Presents info about a member to the user.
   */
  private void showMemberInfo() {
    try {
      Member member = registry.getMember(memberId);
      console.printMemberInfo(member);
      doMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu();
    }
  }

  /**
   * Lets the user add an item to a member.
   */
  private void addItem() {
    String name = dataFetcher.getName();
    String description = dataFetcher.getDescription();
    ItemType type = dataFetcher.getItemType();
    int costPerDay = dataFetcher.getCostPerDay();

    try {
      Item item = registry.createItem(name, description, type, costPerDay);
      registry.addItemToMember(item, memberId);
      console.printMessage("Item was successfully added!");
      doMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu();
    }
  }

  /**
   * Lets the user select an item to work further with.
   */
  private void selectItem() {
    String itemId = dataFetcher.getItemId();
    
    try {
      Item item = registry.getItem(itemId);
      ItemMenu itemMenu = new ItemMenu(item.getId(), memberId, this, registry);
      itemMenu.doItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu();
    }
  }
}
