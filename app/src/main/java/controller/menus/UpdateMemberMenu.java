package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Registry;
import view.Console;
import view.menuchoices.UpdateMemberChoice;

/**
 * Represents an update memeber menu.
 */
public class UpdateMemberMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private String memberId;
  private MemberMenu memberMenu;
  private Registry registry;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "false positive.")
  public UpdateMemberMenu(String memberId, MemberMenu memberMenu, Registry registry) {
    this.memberId = memberId;
    this.memberMenu = memberMenu;
    this.registry = registry;
  }

  /**
   * Gets the update member menu choice from the user and executes it.
   */
  public void doUpdateMemberMenu() {
    UpdateMemberChoice choice = dataFetcher.getUpdateMemberChoice();

    switch (choice) {
      case NAME: 
        updateName();
        break;
      case EMAIL:
        updateEmail();
        break;
      case PHONE:
        updatePhoneNumber();
        break;
      case CANCEL:
        memberMenu.doMemberMenu();
        break;
      default:
        break;
    }
  }

  /**
   * Lets the user update the member's name.
   */
  private void updateName() {
    String name = dataFetcher.getName();
    try {
      registry.updateMemberName(memberId, name);
      console.printMessage("Name was successfully updated!");
      doUpdateMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu();
    }
  }

  /**
   * Lets the user update the member's email.
   */
  private void updateEmail() {
    String email = dataFetcher.getEmail();
    try {
      registry.updateMemberEmail(memberId, email);
      console.printMessage("Email was successfully updated!");
      doUpdateMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu();
    }
  }

  /**
   * Lets the user update the member's phone number.
   */
  private void updatePhoneNumber() {
    String phoneNumber = dataFetcher.getPhoneNumber();
    try {
      registry.updateMemberPhoneNumber(memberId, phoneNumber);
      console.printMessage("Phone number was successfully updated!");
      doUpdateMemberMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMemberMenu();
    }
  }
}
