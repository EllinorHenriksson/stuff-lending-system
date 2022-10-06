import model.Day;
import model.Interval;
import model.Item;
import model.ItemType;
import model.Member;
import model.Registry;

public class TestApp {
  public static void main(String[] args) {
    try {
      Registry registry = new Registry();

      Member member1 = new Member("Ellen", "ellen@lnu.se", "123", "111aaa", new Day(0));
      registry.addMember(member1);
  
      Member member2 = new Member("Emma", "emma@lnu.se", "456", "222bbb", new Day(0));
      registry.addMember(member2);
  
      Item item1 = registry.createItem("Bike", "cool", ItemType.SPORT, 10, new Day(0));
      registry.addItemToMember(item1, "111aaa");

      Item item2 = registry.createItem("Car", "cool", ItemType.SPORT, 10, new Day(0));
      registry.addItemToMember(item2, "222bbb");
  
      registry.addContractToItem(item1.getId(), new Interval(new Day(3), new Day(5)), "222bbb");
      System.out.println(member2.getCredits());
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
