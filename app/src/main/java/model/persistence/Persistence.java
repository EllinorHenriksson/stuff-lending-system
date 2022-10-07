package model.persistence;

import java.util.ArrayList;
import model.Registry;
import model.Day;
import model.Interval;
import model.Item;
import model.ItemType;
import model.Member;

public class Persistence implements IfPersistence {

  @Override
  public void loadDataToRegistry(Registry registry) {

    try {
      Member m1 = registry.createMember("Ellen Nu", "ellen@lnu.se", "1234567", new Day(0));
      registry.addMember(m1);
      Item i1 = registry.createItem("Bike", "Cool", ItemType.SPORT, 50, new Day(0));
      registry.addItemToMember(i1, m1.getId());
      Item i2 = registry.createItem("Tent", "Hilleberg", ItemType.SPORT, 10, new Day(0));
      registry.addItemToMember(i2, m1.getId());

      Member m2 = registry.createMember("Emma Nu", "emma@lnu.se", "5387645837", new Day(0));
      registry.addMember(m2);
    
      Member m3 = registry.createMember("Ellinor Nu", "ellinor@lnu.se", "544645837", new Day(0));
      registry.addMember(m3);
      Item i3 = registry.createItem("Drill", "Effective", ItemType.TOOL, 30, new Day(0));
      registry.addItemToMember(i3, m3.getId());

      registry.addContractToItem(i2.getId(), new Interval(new Day(5), new Day(7)), m3.getId());

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
  }

  @Override
  public void saveDataFromRegistry(Registry registry) {
  }
}
