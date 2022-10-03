package model;

import java.util.ArrayList;

public class Item {
  private String name;
  private String description;
  private Day creationDay;
  private int costPerDay;
  private Type type;
  private Member owner;
  private ArrayList<Contract> contracts;

  public Item (String name, String description, Day creationDay, int costPerDay, Type type) {
    setName(name);
    setDescription(description);
    setCreationDay(creationDay);
    setCostPerDay(costPerDay);
    this.type = type;
    contracts = new ArrayList<>();
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must be specified.");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("The description must be specified.");
    }
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  private void setCreationDay(Day creationDay) {
    if (creationDay == null) {
      throw new IllegalArgumentException("The creation day must be specified.");
    }
    this.creationDay = creationDay;
  }

  public Day getCreationDay() {
    return new Day(creationDay.getDayNumber());
  }

  public void setCostPerDay(int costPerDay) {
    if (costPerDay < 0) {
      throw new IllegalArgumentException("The cost per day must be a positive number.");
    }
    this.costPerDay = costPerDay;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public void setOwner(Member owner) {
    if (owner == null) {
      throw new IllegalArgumentException("Owner must be specified.");
    }

    this.owner = owner;
  }

  public Member getOwner() {
    return new Member(owner.getName(), owner.getEmail(), owner.getPhoneNumber(), owner.getId(), owner.getCreationDay());
  }

  // public boolean isAvailable() {

  // }

}
