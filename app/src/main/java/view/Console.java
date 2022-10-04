package view;

public class Console {
  public void presentMainMenu() {
    String menu = "*** Main Menu ***\n"
        + "simple : List all members\n"
        + "full : List all members (full info)\n"
        + "add : Add new member\n"
        + "select : Select a member\n"
        + "time : Advance time";

    System.out.println(menu);
  }

  public void presentMemberMenu() {
    String menu = "*** Member Menu ***\n"
        + "delete : Delete member\n"
        + "update : Update member info\n"
        + "info : Show member info\n"
        + "add : Add item to member";

    System.out.println(menu);
  }

  public void presentItemMenu() {
    String menu = "*** Item Menu ***\n"
        + "delete : Delete item\n"
        + "update : Update item info\n"
        + "info : Show item info\n"
        + "contract : Establish contract";

    System.out.println(menu);
  }
}
