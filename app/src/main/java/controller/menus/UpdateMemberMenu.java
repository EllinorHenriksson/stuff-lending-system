package controller.menus;

import model.DayCounter;
import model.Registry;
import view.Console;
import view.menuChoices.UpdateMemberChoice;

public class UpdateMemberMenu {
  private Console console = new Console();
  private DataFetcher dataFetcher = new DataFetcher();

  private MemberMenu memberMenu;
  private Registry registry;
  private DayCounter dayCounter;

  public UpdateMemberMenu(MemberMenu memberMenu, Registry registry, DayCounter dayCounter) {
    this.memberMenu = memberMenu;
    this.registry = registry;
    this.dayCounter = dayCounter;
  }

  public void doUpdateMemberMenu(String id) {
    UpdateMemberChoice choice = null;
    while (choice == null) {
      console.presentUpdateMemberMenu();
      try {
        choice = console.getUpdateMemberChoice();
      } catch (Exception e) {
        console.presentErrorMessage(e.getMessage());
      }  
    }

    switch (choice) {
      case NAME: 
        updateName(id);
        break;
      case EMAIL:
        updateEmail(id);
        break;
      case PHONE:
        updatePhoneNumber(id);
        break;
      case CANCEL:
        memberMenu.doMemberMenu(id);
        break;
      default:
        break;
    }
  }

  private void updateName(String id) {
    String name = dataFetcher.getName();
    try {
      registry.updateMemberName(id, name);
      console.presentMessage("Name was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(id);
    }
  }

  private void updateEmail(String id) {
    String email = dataFetcher.getEmail();
    try {
      registry.updateMemberEmail(id, email);
      console.presentMessage("Email was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(id);
    }
  }

  private void updatePhoneNumber(String id) {
    String phoneNumber = dataFetcher.getPhoneNumber();
    try {
      registry.updateMemberPhoneNumber(id, phoneNumber);
      console.presentMessage("Phone number was successfully updated!");
      doUpdateMemberMenu(id);
    } catch (Exception e) {
      console.presentErrorMessage(e.getMessage());
      memberMenu.doMemberMenu(id);
    }
  }
}
