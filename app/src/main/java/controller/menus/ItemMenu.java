package controller.menus;

import model.Interval;
import model.Item;
import model.Registry;
import view.Console;
import view.menuChoices.ItemChoice;

public class ItemMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private MemberMenu memberMenu;
  private Registry registry;
  private UpdateItemMenu updateItemMenu;

  public ItemMenu(MemberMenu memberMenu, Registry registry) {
    this.memberMenu = memberMenu;
    this.registry = registry;

    updateItemMenu = new UpdateItemMenu(this, registry);
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

  private void deleteItem(String itemId, String memberId) {
    try {
      registry.removeItemFromMember(itemId, memberId);
      console.presentMessage("Item was successfully deleted!");
      memberMenu.doMemberMenu(memberId);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      doItemMenu(itemId, memberId);
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

  private void establishContract(String itemId, String ownerId) {
    String lenderId = dataFetcher.getLenderId();
    Interval interval = dataFetcher.getInterval();

    registry.addContractToItem(itemId, interval, lenderId);
    console.presentMessage("Contract was successfully established!");
    doItemMenu(itemId, ownerId);
  }
}
