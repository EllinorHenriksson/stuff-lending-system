package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Item;
import model.ItemType;
import model.Member;
import model.Registry;
import view.menuchoices.MemberChoice;

/**
 * Represents a member menu.
 */
public class MemberMenu extends Menu {
  private String memberId;
  private MainMenu mainMenu;

  /**
   * Initializing constructor.
   *
   * @param memberId The ID of the current member.
   * @param mainMenu The main menu to work with.
   * @param registry The registry to work with.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "false positive.")
  public MemberMenu(String memberId, MainMenu mainMenu, Registry registry) {
    super(registry);
    this.memberId = memberId;
    this.mainMenu = mainMenu;
  }

  /**
   * Gets the member menu choice from the user and executes it.
   */
  public void doMenu() {
    MemberChoice choice = dataFetcher.getMemberChoice();

    switch (choice) {
      case DELETE: 
        deleteMember();
        break;
      case UPDATE:
        updateMember();
        break;
      case INFO:
        showMemberInfo();
        break;
      case ADD:
        addItem();
        break;
      case SELECT:
        selectItem();
        break;
      case MAIN:
        mainMenu.doMenu();
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user delete a member.
   */
  private void deleteMember() {
    try {
      registry.removeMember(memberId);
      console.printMessage("Member was successfully deleted!");
      mainMenu.doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }

  private void updateMember() {
    UpdateMemberMenu updateMemberMenu = new UpdateMemberMenu(memberId, this, registry);
    updateMemberMenu.doMenu();
  }

  /**
   * Presents info about a member to the user.
   */
  private void showMemberInfo() {
    try {
      Member member = registry.getMember(memberId);
      console.printMemberInfo(member);
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }

  /**
   * Lets the user add an item to a member.
   */
  private void addItem() {
    String name = dataFetcher.getName();
    String description = dataFetcher.getDescription();
    ItemType type = dataFetcher.getItemType();
    int costPerDay = dataFetcher.getCostPerDay();

    try {
      Item item = registry.createItem(name, description, type, costPerDay);
      registry.addItemToMember(item, memberId);
      console.printMessage("Item was successfully added!");
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }

  /**
   * Lets the user select an item to work further with.
   */
  private void selectItem() {
    String itemId = dataFetcher.getItemId();
    
    try {
      Item item = registry.getItem(itemId);
      ItemMenu itemMenu = new ItemMenu(item.getId(), memberId, this, registry);
      itemMenu.doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      doMenu();
    }
  }
}
