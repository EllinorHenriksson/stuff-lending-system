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

  private String itemId;

  private ItemMenu itemMenu;
  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param itemMenu The item menu to work with.
   * @param registry The registry to work with.
   */
  public UpdateItemMenu(String itemId, ItemMenu itemMenu, Registry registry) {
    this.itemId = itemId;
    this.itemMenu = itemMenu;
    this.registry = registry;
  }

  /**
   * Gets the update item menu choice from the user and executes it.
   */
  public void doUpdateItemMenu() {
    UpdateItemChoice choice = dataFetcher.getUpdateItemChoice();

    switch (choice) {
      case NAME: 
        updateItemName();
        break;
      case DESCRIPTION:
        updateItemDescription();
        break;
      case TYPE:
        updateItemType();
        break;
      case COST:
        updateCostPerDay();
        break;
      case CANCEL:
        itemMenu.doItemMenu();
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user update the item name.
   */
  private void updateItemName() {
    String name = dataFetcher.getName();
    try {
      registry.updateItemName(itemId, name);
      console.printMessage("Name was successfully updated!");
      doUpdateItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu();
    }    
  }

  /**
   * Lets the user update the item description.
   */
  private void updateItemDescription() {
    String description = dataFetcher.getDescription();
    try {
      registry.updateItemDescription(itemId, description);
      console.printMessage("Description was successfully updated!");
      doUpdateItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu();
    }    
  }

  /**
   * Lets the user update the item type.
   */
  private void updateItemType() {
    ItemType itemType = dataFetcher.getItemType();
    try {
      registry.updateItemType(itemId, itemType);
      console.printMessage("Type was successfully updated!");
      doUpdateItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu();
    }    
  }

  /**
   * Lets the user update the item's cost per day.
   */
  private void updateCostPerDay() {
    int costPerDay = dataFetcher.getCostPerDay();
    try {
      registry.updateItemCostPerDay(itemId, costPerDay);
      console.printMessage("Cost per day was successfully updated!");
      doUpdateItemMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doItemMenu();
    }    
  }
}
