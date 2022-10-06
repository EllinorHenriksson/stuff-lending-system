package model;

import java.util.ArrayList;

public class Item {
  private String name;
  private String description;
  private Day creationDay;
  private int costPerDay;
  private ItemType type;
  private String id;
  private Member owner;
  private ArrayList<Contract> contracts;
  private Validator validator = new Validator();

  public Item(String name, String description, Day creationDay, int costPerDay, ItemType type, String id) {
    setName(name);
    setDescription(description);
    setCreationDay(creationDay);
    setCostPerDay(costPerDay);
    setId(id);
    setType(type);
    contracts = new ArrayList<>();
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public ItemType getType() {
    return type;
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

  private void setId(String id) {
    validator.validateId(id);
    this.id = id;
  }

  public String getId() {
    return id;
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

  public void payTotalCost(int totalCost) {
    owner.addCredits(totalCost);
  }

  public Contract createContract(Member lender, Interval interval) throws Exception {

    if (lender == null) {
      throw new IllegalArgumentException("Lender must be specified.");
    }

    if (interval == null) {
      throw new IllegalArgumentException("Interval must be specified.");
    }

    if (isAvailable(interval)) {
      transferCredits(interval, lender);
      return new Contract(this, interval, lender);
    } else {
      throw new Exception("Item not available during the specified interval.");
    }

  }

  private void transferCredits(Interval interval, Member lender) throws Exception {
    int totalCost = this.getCostPerDay() * interval.getNumberOfDays();
    if (lender.getCredits() >= totalCost) {
      throw new Exception("The lender doesn't have enough credits to lend the item.");
    }

    lender.removeCredits(totalCost);
    payTotalCost(totalCost);
  }

  public void addContract(Contract contract) {
    for (Contract c : contracts) {
      if (contract == c) {
        throw new IllegalArgumentException("The contract is already added to the item.");
      }
    }

    contracts.add(contract);
  }

  public ArrayList<Contract> getContracts() {
    ArrayList<Contract> copies = new ArrayList<>();

    for (Contract contract : contracts) {
      copies.add(new Contract(this, contract.getInterval(), contract.getLender()));
    }

    return copies;
  }

  public boolean isAvailable(Interval interval) {
    for (Contract contract : contracts) {
      if (contract.getInterval().isOverlappingWith(interval)) {
        return false;
      }
    }
    return true;
  }
}
