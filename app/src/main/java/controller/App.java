package controller;

import model.Member;

public class App {
  public static void main(String[] args) {
    Member member = new Member("Emma Fransson", "emma@student.lnu.se", "1234567", "1x4jfn");
    System.out.print(member.getName() + member.getEmail()
        + member.getPhoneNumber() + member.getId());
  }
}
