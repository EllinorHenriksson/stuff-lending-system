package controller.menus;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Registry;
import view.menuchoices.UpdateMemberChoice;

/**
 * Represents an update memeber menu.
 */
public class UpdateMemberMenu extends Menu {
  private String memberId;
  private MemberMenu memberMenu;

  /**
   * Initializing constructor.
   *
   * @param memberMenu The member menu to work with.
   * @param registry The registry to work with.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "false positive.")
  public UpdateMemberMenu(String memberId, MemberMenu memberMenu, Registry registry) {
    super(registry);
    this.memberId = memberId;
    this.memberMenu = memberMenu;
  }

  /**
   * Gets the update member menu choice from the user and executes it.
   */
  public void doMenu() {
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
        memberMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMenu();
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
      doMenu();
    } catch (Exception e) {
      console.printErrorMessage(e.getMessage());
      memberMenu.doMenu();
    }
  }
}
