package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.ItemType;
import model.Registry;
import view.menuchoices.UpdateItemChoice;

/**
 * Represents an update item menu.
 */
public class UpdateItemMenu extends Menu {
  private String itemId;
  private ItemMenu itemMenu;

  /**
   * Initializing constructor.
   *
   * @param itemMenu The item menu to work with.
   * @param registry The registry to work with.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "false positive.")
  public UpdateItemMenu(String itemId, ItemMenu itemMenu, Registry registry) {
    super(registry);
    this.itemId = itemId;
    this.itemMenu = itemMenu;
  }

  /**
   * Gets the update item menu choice from the user and executes it.
   */
  public void doMenu() {
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
        itemMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      itemMenu.doMenu();
    }    
  }
}
