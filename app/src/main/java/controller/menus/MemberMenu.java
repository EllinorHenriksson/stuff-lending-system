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

  private MainMenu mainMenu;
  private UpdateMemberMenu updateMemberMenu;
  private ItemMenu itemMenu;

  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param mainMenu The main menu to work with.
   * @param registry The registry to work with.
   */
  public MemberMenu(MainMenu mainMenu, Registry registry) {
    this.mainMenu = mainMenu;
    this.registry = registry;

    this.updateMemberMenu = new UpdateMemberMenu(this, registry);
    this.itemMenu = new ItemMenu(this, registry);
  }

  /**
   * Gets the member menu choice from the user and executes it.
   *
   * @param memberId The ID of the current member.
   */
  public void doMemberMenu(String memberId) {
    MemberChoice choice = dataFetcher.getMemberChoice();

    switch (choice) {
      case DELETE: 
        deleteMember(memberId);
        break;
      case UPDATE:
        updateMemberMenu.doUpdateMemberMenu(memberId);
        break;
      case INFO:
        showMemberInfo(memberId);
        break;
      case ADD:
        addItem(memberId);
        break;
      case SELECT:
        selectItem(memberId);
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
   *
   * @param memberId The ID of the member to delete.
   */
  private void deleteMember(String memberId) {
    try {
      registry.removeMember(memberId);
      console.printMessage("Member was successfully deleted!");
      mainMenu.doMainMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(memberId);
    }
  }

  /**
   * Presents info about a member to the user.
   *
   * @param memberId The ID of the member to present.
   */
  private void showMemberInfo(String memberId) {
    try {
      Member member = registry.getMember(memberId);
      console.printMemberInfo(member);
      doMemberMenu(memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(memberId);
    }
  }

  /**
   * Lets the user add an item to a member.
   *
   * @param memberId - The ID of the member.
   */
  private void addItem(String memberId) {
    String name = dataFetcher.getName();
    String description = dataFetcher.getDescription();
    ItemType type = dataFetcher.getItemType();
    int costPerDay = dataFetcher.getCostPerDay();

    try {
      Item item = registry.createItem(name, description, type, costPerDay);
      registry.addItemToMember(item, memberId);
      console.printMessage("Item was successfully added!");
      doMemberMenu(memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(memberId);
    }
  }

  /**
   * Lets the user select an item to work further with.
   *
   * @param ownerId The ID of the member owning the item.
   */
  private void selectItem(String ownerId) {
    String itemId = dataFetcher.getItemId();
    
    try {
      Item item = registry.getItem(itemId);
      itemMenu.doItemMenu(item.getId(), ownerId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(ownerId);
    }
  }
}
