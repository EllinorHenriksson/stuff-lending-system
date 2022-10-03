package controller;

import java.util.ArrayList;

import model.DayCounter;
import model.Member;
import model.Registry;

public class App {
  public static void main(String[] args) {

    try {
      DayCounter dayCounter = new DayCounter();
      Registry registry =  new Registry();
      Member member = registry.createMember("Emma Fransson", "emma@student.lnu.se", "1234567", dayCounter.getCurrentDay());
  
      System.out.println("Member emma: " + member);
      
      registry.addMember(member);
  
      Member emmaCopy = registry.getMember(member.getId());
      System.out.println("Member emma copy: " + emmaCopy);
  
      Member member2 = registry.createMember("Ellen Nu", "lnu@student.lnu.se", "875764754", dayCounter.getCurrentDay());
  
      System.out.println("Member 2: " + member2);
  
      registry.addMember(member2);
  
      ArrayList<Member> members = registry.getMembers();
      
      for (Member m : members) {
        System.out.println(m);
      }

      System.out.println(member.getEmail());
      registry.updateEmail(member.getId(), "frasse@lnu.se");
      System.out.println(member.getEmail());

      System.out.println(member.getPhoneNumber());
      registry.updatePhoneNumber(member.getId(), "9876543");
      System.out.println(member.getPhoneNumber());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
