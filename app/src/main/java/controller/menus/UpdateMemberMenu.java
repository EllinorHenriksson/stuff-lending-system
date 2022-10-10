package controller.menus;

import model.Registry;
import view.Console;
import view.menuchoices.UpdateMemberChoice;

/**
 * Represents an update memeber menu.
 */
public class UpdateMemberMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private MemberMenu memberMenu;
  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  public UpdateMemberMenu(MemberMenu memberMenu, Registry registry) {
    this.memberMenu = memberMenu;
    this.registry = registry;
  }

  /**
   * Gets the update member menu choice from the user and executes it.
   *
   * @param memberId The ID of the member to update.
   */
  public void doUpdateMemberMenu(String memberId) {
    UpdateMemberChoice choice = dataFetcher.getUpdateMemberChoice();

    switch (choice) {
      case NAME: 
        updateName(memberId);
        break;
      case EMAIL:
        updateEmail(memberId);
        break;
      case PHONE:
        updatePhoneNumber(memberId);
        break;
      case CANCEL:
        memberMenu.doMemberMenu(memberId);
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user update the member's name.
   *
   * @param memberId The ID of the member to update.
   */
  private void updateName(String memberId) {
    String name = dataFetcher.getName();
    try {
      registry.updateMemberName(memberId, name);
      console.printMessage("Name was successfully updated!");
      doUpdateMemberMenu(memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(memberId);
    }
  }

  /**
   * Lets the user update the member's email.
   *
   * @param memberId The ID of the member to update.
   */
  private void updateEmail(String memberId) {
    String email = dataFetcher.getEmail();
    try {
      registry.updateMemberEmail(memberId, email);
      console.printMessage("Email was successfully updated!");
      doUpdateMemberMenu(memberId);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(memberId);
    }
  }

  /**
   * Lets the user update the member's phone number.
   *
   * @param memberId The ID of the member to update.
   */
  private void updatePhoneNumber(String id) {
    String phoneNumber = dataFetcher.getPhoneNumber();
    try {
      registry.updateMemberPhoneNumber(id, phoneNumber);
      console.printMessage("Phone number was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(id);
    }
  }
}
