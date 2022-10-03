package controller;

import model.IdGenerator;
import model.Member;

public class App {
  public static void main(String[] args) {

    IdGenerator idGenerator = new IdGenerator(6);

    Member member = new Member("Emma Fransson", "emma@student.lnu.se", "1234567", idGenerator.generateId());
    System.out.print(member.getName() + member.getEmail()
        + member.getPhoneNumber() + member.getId());

    
  }
}
