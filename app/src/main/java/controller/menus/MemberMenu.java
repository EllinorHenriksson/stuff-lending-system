package controller.menus;

import model.Item;
import model.ItemType;
import model.Member;
import model.Registry;
import view.Console;
import view.menuChoices.MemberChoice;

public class MemberMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private MainMenu mainMenu;
  private UpdateMemberMenu updateMemberMenu;
  private ItemMenu itemMenu;

  private Registry registry;

  public MemberMenu(MainMenu mainMenu, Registry registry) {
    this.mainMenu = mainMenu;
    this.registry = registry;

    this.updateMemberMenu = new UpdateMemberMenu(this, registry);
    this.itemMenu = new ItemMenu(this, registry);
  }

  public void doMemberMenu(String id) {
    MemberChoice choice = dataFetcher.getMemberChoice();

    switch (choice) {
      case DELETE: 
        deleteMember(id);
        break;
      case UPDATE:
        updateMemberMenu.doUpdateMemberMenu(id);
        break;
      case INFO:
        showMemberInfo(id);
        break;
      case ADD:
        addItem(id);
        break;
      case SELECT:
        selectItem(id);
        break;
      case MAIN:
        mainMenu.doMainMenu();
        break;
      default:
        break;
    }
  }

  private void deleteMember(String id) {
    try {
      registry.removeMember(id);
      console.printMessage("Member was successfully deleted!");
      mainMenu.doMainMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private void showMemberInfo(String id) {
    try {
      Member member = registry.getMember(id);
      console.printMemberInfo(member);
      doMemberMenu(id);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

  private void addItem(String id) {
    String name = dataFetcher.getName();
    String description = dataFetcher.getDescription();
    ItemType type = dataFetcher.getItemType();
    int costPerDay = dataFetcher.getCostPerDay();

    try {
      Item item = registry.createItem(name, description, type, costPerDay);
      registry.addItemToMember(item, id);
      console.printMessage("Item was successfully added!");
      doMemberMenu(id);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMemberMenu(id);
    }
  }

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
