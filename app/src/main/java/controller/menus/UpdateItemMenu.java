package controller.menus;

import model.DayCounter;
import model.ItemType;
import model.Registry;
import view.Console;
import view.menuChoices.UpdateItemChoice;

public class UpdateItemMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private ItemMenu itemMenu;
  private Registry registry;

  public UpdateItemMenu(ItemMenu itemMenu, Registry registry) {
    this.itemMenu = itemMenu;
    this.registry = registry;
  }

  public void doUpdateItemMenu(String itemId, String memberId) {
    UpdateItemChoice choice = null;
    while (choice == null) {
      console.printUpdateItemMenu();
      try {
        choice = console.getUpdateItemChoice();
      } catch (Exception e) {
        console.printErrorMessage(e.getMessage());
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
        itemMenu.doItemMenu(itemId, memberId);
        break;
      default:
        break;
    }
  }

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
