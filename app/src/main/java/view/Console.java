package view;

import java.util.ArrayList;
import model.Day;
import model.Item;
import model.ItemType;
import model.Member;
import view.menuchoices.ItemChoice;
import view.menuchoices.MainChoice;
import view.menuchoices.MemberChoice;
import view.menuchoices.UpdateItemChoice;
import view.menuchoices.UpdateMemberChoice;

/**
 * Class for console object.
 */
public class Console {
  private Input input = new Input();
  private Output output = new Output();

  /**
   * Prints the main menu.
   */
  public void printMainMenu() {
    output.printMainMenu();
  }

  /**
   * Prints member menu.
   */
  public void printMemberMenu() {
    output.printMemberMenu();
  }

  /**
   * Prints item menu.
   */
  public void printItemMenu() {
    output.printItemMenu();
  }

  /**
   * Prints update member menu.
   */
  public void printUpdateMemberMenu() {
    output.printUpdateMemberMenu();
  }

  /**
   * Prints update item menu.
   */
  public void printUpdateItemMenu() {
    output.printUpdateItemMenu();
  }

  /**
   * Promts the user for main menu choice.
   */
  public MainChoice getMainChoice() throws Exception {
    return input.getMainChoice();
  }

  /**
   * Promts the user for member menu choice.
   */
  public MemberChoice getMemberChoice() throws Exception {
    return input.getMemberChoice();
  }

  /**
   * Promts the user for item menu choice.
   */
  public ItemChoice getItemChoice() throws Exception {
    return input.getItemChoice();
  }

  /**
   * Promts the user for update member menu choice.
   */
  public UpdateMemberChoice getUpdateMemberChoice() throws Exception {
    return input.getUpdateMemberChoice();
  }

  /**
   * Promts the user for update item menu choice.
   */
  public UpdateItemChoice getUpdateItemChoice() throws Exception {
    return input.getUpdateItemChoice();
  }

  /**
   * Prints the string message to the terminal.
   *
   * @param message the string to print.
   */
  public void printMessage(String message) {
    output.printMessage(message);
  }

  /**
   * Prints a error message to the terminal. 
   *
   * @param message The error message to print.
   */
  public void printErrorMessage(String message) {
    output.printErrorMessage(message);
  }

  /**
   * Prints the current day. 
   *
   * @param currentDay the day to print.
   */
  public void printCurrentDay(Day currentDay) {
    output.printCurrentDay(currentDay);
  }

  /**
   * Promts the user fo a name.
   *
   * @return String.
   */
  public String getName() {
    return input.getName();
  }

  /**
   * Promts the user for an email. 
   *
   * @return String.
   */
  public String getEmail() {
    return input.getEmail();
  }

  /**
   * Promts the user for a phone number. 
   *
   * @return String.
   */
  public String getPhoneNumber() {
    return input.getPhoneNumber();
  }

  /**
   * Promts the user for a member id.
   *
   * @return String.
   */
  public String getMemberId() {
    return input.getMemberId();
  }

  /**
   * Promts the user for a number of days.
   *
   * @return int.
   * @throws Exception Exception.
   */
  public int getNumberOfDays() throws Exception {
    return input.getNumberOfDays();
  }

  /**
   * Promts the user for a member id.
   *
   * @return string.
   */
  public String getItemId() {
    return input.getItemId();
  }

  /**
   * Promts the user for a description.
   *
   * @return String.
   */
  public String getDescription() {
    return input.getDescription();
  }

  /**
   * Promts the user for a type.
   *
   * @return Type.
   * @throws Exception Exception.
   */
  public ItemType getType() throws Exception {
    return input.getType();
  }

  /**
   * Promts the user for the cost per day for the item.
   *
   * @return int.
   * @throws Exception Exception.
   */
  public int getCostPerDay() throws Exception {
    return input.getCostPerDay();
  }

  /**
   * Promts the user for a lender id.
   *
   * @return String.
   */
  public String getLenderId() {
    return input.getLenderId();
  }

  /**
   * Promts the user for a start day. 
   *
   * @return Day.
   */
  public Day getStartDay() {
    return input.getStartDay();
  }

  /**
   * Promts the user for the end day.
   *
   * @return Day.
   */
  public Day getEndDay() {
    return input.getEndDay();
  }

  /**
   * Prints a simple list of all members.
   *
   * @param members ArrayList.
   */
  public void printMembersSimple(ArrayList<Member> members) {
    output.printMembersSimple(members);
  }

  /**
   * Prints a full list of all members.
   *
   * @param members ArrayList.
   */
  public void printMembersFull(ArrayList<Member> members) {
    output.printMembersFull(members);
  }

  /**
   * Prints the information for one member.
   *
   * @param member Member.
   */
  public void printMemberInfo(Member member) {
    output.printMemberInfo(member);
  }

  /**
   * Prints the information for one item.
   *
   * @param item Item.
   */
  public void printItemInfo(Item item) {
    output.printItemInfo(item);
  }
}


