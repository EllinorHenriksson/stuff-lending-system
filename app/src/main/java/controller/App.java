package controller;

import java.util.ArrayList;

import model.Day;
import model.DayCounter;
import model.Interval;
import model.Item;
import model.Member;
import model.Registry;
import model.Type;
import model.persistence.Persistence;

public class App {
  public static void main(String[] args) {

    try {
      DayCounter dayCounter = new DayCounter();
      Registry registry =  new Registry();

      ArrayList<Member> members = new Persistence().loadData();
      for (Member m : members) {
        registry.addMember(m);
      }

      for (Member m : registry.getMembers()) {
        System.out.println(m.getName());
      }
      
      /*
      Member member1 = registry.createMember("Emma Fransson", "emma@student.lnu.se", "1234567", dayCounter.getCurrentDay());
      Member member2 = registry.createMember("Ellen Nu", "ellennu@student.lnu.se", "1111111", dayCounter.getCurrentDay());
  
      Item item = member1.createItem("Tent", "A nice tent", dayCounter.getCurrentDay(), 10, Type.OTHER);
      member1.addItem(item);

      item.addContract(item.createContract(member2, new Interval(new Day(3), new Day(5))));
      item.addContract(item.createContract(member2, new Interval(new Day(2), new Day(4))));
      */
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
