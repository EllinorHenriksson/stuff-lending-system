package view;

import java.util.ArrayList;

import model.Day;
import model.Item;
import model.ItemType;
import model.Member;
import view.menuChoices.ItemChoice;
import view.menuChoices.MainChoice;
import view.menuChoices.MemberChoice;
import view.menuChoices.UpdateItemChoice;
import view.menuChoices.UpdateMemberChoice;

public class Console {
  private Input input = new Input();
  private Output output = new Output();

  public void printMainMenu() {
    output.printMainMenu();
  }

  public void printMemberMenu() {
    output.printMemberMenu();
  }

  public void printItemMenu() {
    output.printItemMenu();
  }

  public void printUpdateMemberMenu() {
    output.printUpdateMemberMenu();
  }

  public void printUpdateItemMenu() {
    output.printUpdateItemMenu();
  }

  public MainChoice getMainChoice() throws Exception {
    return input.getMainChoice();
  }

  public MemberChoice getMemberChoice() throws Exception {
    return input.getMemberChoice();
  }

  public ItemChoice getItemChoice() throws Exception {
    return input.getItemChoice();
  }

  public UpdateMemberChoice getUpdateMemberChoice() throws Exception {
    return input.getUpdateMemberChoice();
  }

  public UpdateItemChoice getUpdateItemChoice() throws Exception {
    return input.getUpdateItemChoice();
  }

  public void printMessage(String message) {
    output.printMessage(message);
  }

  public void printErrorMessage(String message) {
    output.printErrorMessage(message);
  }

  public void printCurrentDay(Day day) {
    output.printCurrentDay(day);
  }

  public String getName() {
    return input.getName();
  }

  public String getEmail() {
    return input.getEmail();
  }

  public String getPhoneNumber() {
    return input.getPhoneNumber();
  }

  public String getMemberId() {
    return input.getMemberId();
  }

  public String getItemId() {
    return input.getItemId();
  }

  public String getDescription() {
    return input.getDescription();
  }

  public ItemType getType() throws Exception {
    return input.getType();
  }

  public int getCostPerDay() throws Exception {
    return input.getCostPerDay();
  }

  public String getLenderId() {
    return input.getLenderId();
  }

  public Day getStartDay() {
    return input.getStartDay();
  }

  public Day getEndDay() {
    return input.getEndDay();
  }

  public void printMembersSimple(ArrayList<Member> members) {
    output.printMembersSimple(members);
  }

  public void printMembersFull(ArrayList<Member> members) {
    output.printMembersFull(members);
  }

  public void printMemberInfo(Member member) {
    output.printMemberInfo(member);
  }

  public void printItemInfo(Item item) {
    output.printItemInfo(item);
  }



 
}


