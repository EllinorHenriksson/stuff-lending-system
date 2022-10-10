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

  private MemberMenu memberMenu;
  private Registry registry;
  private UpdateItemMenu updateItemMenu;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  public ItemMenu(MemberMenu memberMenu, Registry registry) {
    this.memberMenu = memberMenu;
    this.registry = registry;

    updateItemMenu = new UpdateItemMenu(this, registry);
  }

  /**
   * Gets the item menu choice from the user and executes it.
   *
   * @param itemId The ID of the current item.
   * @param memberId The ID of the member owning the current item.
   */
  public void doItemMenu(String itemId, String memberId) {
    ItemChoice choice = dataFetcher.getItemChoice();

    switch (choice) {
      case DELETE: 
        deleteItem(itemId, memberId);
        break;
      case UPDATE:
        updateItemMenu.doUpdateItemMenu(itemId, memberId);
        break;
      case INFO:
        showItemInfo(itemId, memberId);
        break;
      case CONTRACT:
        establishContract(itemId, memberId);
        break;
      case MEMBER:
        memberMenu.doMemberMenu(memberId);
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user remove an item from a member.
   *
   * @param itemId The ID of the item.
   * @param memberId The ID of the member owning the item.
   */
  private void deleteItem(String itemId, String memberId) {
    try {
      registry.removeItemFromMember(itemId, memberId);
      console.printMessage("Item was successfully deleted!");
      memberMenu.doMemberMenu(memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }
  }

  /**
   * Presents info about an item to the user.
   *
   * @param itemId - The item ID.
   * @param memberId - The ID of the member owning the item.
   */
  private void showItemInfo(String itemId, String memberId) {
    try {
      Item item = registry.getItem(itemId);
      console.printItemInfo(item);
      doItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
    }
  }

  /**
   * Lets the user establish a lending contract for an item.
   *
   * @param itemId The ID of the item to lend.
   * @param ownerId The ID of the member owning the item.
   */
  private void establishContract(String itemId, String ownerId) {
    String lenderId = dataFetcher.getLenderId();
    Interval interval = dataFetcher.getInterval();
    try {
      registry.establishContractForItem(itemId, interval, lenderId);
      console.printMessage("Contract was successfully established!");
      doItemMenu(itemId, ownerId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doItemMenu(itemId, ownerId);    
    }
  }
}
