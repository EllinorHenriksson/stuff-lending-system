package controller.menus;

import model.ItemType;
import model.Registry;
import view.Console;
import view.menuchoices.UpdateItemChoice;

/**
 * Represents an update item menu.
 */
public class UpdateItemMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private ItemMenu itemMenu;
  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param itemMenu The item menu to work with.
   * @param registry The registry to work with.
   */
  public UpdateItemMenu(ItemMenu itemMenu, Registry registry) {
    this.itemMenu = itemMenu;
    this.registry = registry;
  }

  /**
   * Gets the update item menu choice from the user and executes it.
   *
   * @param itemId The ID of the item to update.
   * @param memberId The ID of the member owning the item.
   */
  public void doUpdateItemMenu(String itemId, String memberId) {
    UpdateItemChoice choice = dataFetcher.getUpdateItemChoice();

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
        itemMenu.doItemMenu(itemId, memberId);
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user update the item name.
   *
   * @param itemId The ID of the item to update.
   * @param memberId The ID of the member owning the item.
   */
  private void updateItemName(String itemId, String memberId) {
    String name = dataFetcher.getName();
    try {
      registry.updateItemName(itemId, name);
      console.printMessage("Name was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu(itemId, memberId);
    }    
  }

  /**
   * Lets the user update the item description.
   *
   * @param itemId The ID of the item to update.
   * @param memberId The ID of the member owning the item.
   */
  private void updateItemDescription(String itemId, String memberId) {
    String description = dataFetcher.getDescription();
    try {
      registry.updateItemDescription(itemId, description);
      console.printMessage("Description was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu(itemId, memberId);
    }    
  }

  /**
   * Lets the user update the item type.
   *
   * @param itemId The ID of the item to update.
   * @param memberId The ID of the member owning the item.
   */
  private void updateItemType(String itemId, String memberId) {
    ItemType itemType = dataFetcher.getItemType();
    try {
      registry.updateItemType(itemId, itemType);
      console.printMessage("Type was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu(itemId, memberId);
    }    
  }

  /**
   * Lets the user update the item's cost per day.
   *
   * @param itemId The ID of the item to update.
   * @param memberId The ID of the member owning the item.
   */
  private void updateCostPerDay(String itemId, String memberId) {
    int costPerDay = dataFetcher.getCostPerDay();
    try {
      registry.updateItemCostPerDay(itemId, costPerDay);
      console.printMessage("Cost per day was successfully updated!");
      doUpdateItemMenu(itemId, memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu(itemId, memberId);
    }    
  }
}
