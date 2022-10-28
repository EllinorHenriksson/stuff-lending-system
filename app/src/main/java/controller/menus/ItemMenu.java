package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Interval;
import model.Item;
import model.Registry;
import view.menuchoices.ItemChoice;

/**
 * Represents an item menu.
 */
public class ItemMenu extends Menu {
  private String itemId;
  private String memberId;
  private MemberMenu memberMenu;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "false positive.")
  public ItemMenu(String itemId, String memberId, MemberMenu memberMenu, Registry registry) {
    super(registry);
    this.itemId = itemId;
    this.memberId = memberId;
    this.memberMenu = memberMenu;
  }

  /**
   * Gets the item menu choice from the user and executes it.
   */
  public void doMenu() {
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
        memberMenu.doMenu();
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
      memberMenu.doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }

  /**
   * Lets the user update an item.
   */
  private void updateItem() {
    UpdateItemMenu updateItemMenu = new UpdateItemMenu(itemId, this, registry);
    updateItemMenu.doMenu();
  }

  /**
   * Presents info about an item to the user.
   */
  private void showItemInfo() {
    try {
      Item item = registry.getItem(itemId);
      console.printItemInfo(item);
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();    
    }
  }
}
