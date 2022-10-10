package controller.menus;

import model.Interval;
import model.Item;
import model.Registry;
import view.Console;
import view.menuchoices.ItemChoice;

/**
 * Represents an item menu.
 */
public class ItemMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private String itemId;
  private String memberId;

  private MemberMenu memberMenu;
  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  public ItemMenu(String itemId, String memberId, MemberMenu memberMenu, Registry registry) {
    this.itemId = itemId;
    this.memberId = memberId;
    this.memberMenu = memberMenu;
    this.registry = registry;
  }

  /**
   * Gets the item menu choice from the user and executes it.
   */
  public void doItemMenu() {
    ItemChoice choice = dataFetcher.getItemChoice();

    switch (choice) {
      case DELETE: 
        deleteItem();
        break;
      case UPDATE:
        updateItem();
        break;
      case INFO:
        showItemInfo();
        break;
      case CONTRACT:
        establishContract();
        break;
      case MEMBER:
        memberMenu.doMemberMenu();
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user remove an item from a member.
   */
  private void deleteItem() {
    try {
      registry.removeItemFromMember(itemId, memberId);
      console.printMessage("Item was successfully deleted!");
      memberMenu.doMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu();
    }
  }

  /**
   * Lets the user update an item.
   */
  private void updateItem() {
    UpdateItemMenu updateItemMenu = new UpdateItemMenu(itemId, this, registry);
    updateItemMenu.doUpdateItemMenu();
  }

  /**
   * Presents info about an item to the user.
   */
  private void showItemInfo() {
    try {
      Item item = registry.getItem(itemId);
      console.printItemInfo(item);
      doItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu();
    }
  }

  /**
   * Lets the user establish a lending contract for an item.
   */
  private void establishContract() {
    String lenderId = dataFetcher.getLenderId();
    Interval interval = dataFetcher.getInterval();
    try {
      registry.establishContractForItem(itemId, interval, lenderId);
      console.printMessage("Contract was successfully established!");
      doItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu();    
    }
  }
}
