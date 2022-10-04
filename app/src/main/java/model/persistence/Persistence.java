package model.persistence;

import java.util.ArrayList;
import model.Day;
import model.Member;

public class Persistence implements IPersistence {

  @Override
  public ArrayList<Member> loadData() {
    ArrayList<Member> members = new ArrayList<>();
    members.add(new Member("Ellen Nu", "ellen@lnu.se", "1234567", "111aaa", new Day(0)));
    members.add(new Member("Emma Fransson", "emma@lnu.se", "2345678", "222bbb", new Day(0)));
    members.add(new Member("Ellinor Henriksson", "ellinor@lnu.se", "3456789", "333ccc", new Day(0)));
    return members;
  }

  @Override
  public void saveData(ArrayList<Member> members) {
  }
}
