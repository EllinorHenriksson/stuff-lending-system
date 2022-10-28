package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Class for item object.
 */
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

  /**
   * Contructor for item object.
   *
   * @param name String.
   * @param description String.
   * @param type Enum ItemType.
   * @param costPerDay int.
   * @param id String.
   * @param creationDay Day.
   */
  public Item(String name, String description, ItemType type, int costPerDay, String id, Day creationDay) {
    setName(name);
    setDescription(description);
    setType(type);
    setCostPerDay(costPerDay);
    setId(id);
    setCreationDay(creationDay);
    contracts = new ArrayList<>();
  }

  /**
   * Setts the name of item object.
   *
   * @param name String.
   */
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must be specified.");
    }
    this.name = name;
  }

  /**
   * Returns the name of the item obejct.
   *
   * @return String.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the description of an item object.
   *
   * @param description String.
   */
  public void setDescription(String description) {
    validator.validateDescription(description);
    this.description = description;
  }

  /**
   * Returns the description of an item object.
   *
   * @return String.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the type of an item object.
   *
   * @param type Enum ItemType.
   */
  public void setType(ItemType type) {
    this.type = type;
  }

  /**
   * Returns the type of an item object.
   *
   * @return Enum ItemType.
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Sets the cost per day for an item object.
   *
   * @param costPerDay int.
   */
  public void setCostPerDay(int costPerDay) {
    validator.checkPositive(costPerDay);
    this.costPerDay = costPerDay;
  }

  /**
   * Returns the cost per day for an item object.
   *
   * @return int.
   */
  public int getCostPerDay() {
    return costPerDay;
  }

  /**
   * Sets the id of an item object.
   *
   * @param id String.
   */
  private void setId(String id) {
    validator.validateId(id);
    this.id = id;
  }

  /**
   * Returns the id of an item object.
   *
   * @return String.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the creation day of an item object.
   *
   * @param creationDay Day.
   */
  private void setCreationDay(Day creationDay) {
    validator.checkNull(creationDay, "The creation day must be specified.");
    this.creationDay = creationDay;
  }

  /**
   * Returns a copy of the creating day of an item object.
   *
   * @return Day.
   */
  public Day getCreationDay() {
    return new Day(creationDay.getDayNumber());
  }

  /**
   * Sets the owner of an item object.
   *
   * @param owner Member.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "false positive.")
  public void setOwner(Member owner) {
    validator.checkNull(owner, "Owner must be specified.");
    this.owner = owner;
  }

  /**
   * Returns a copy of the owner of a item object.
   *
   * @return Member.
   */
  public Member getOwner() {
    return new Member(owner.getName(), owner.getEmail(), owner.getPhoneNumber(), owner.getId(), owner.getCreationDay());
  }

  /**
   * Pays the owner of an item (when a loan of this item takes place).
   *
   * @param totalCost int.
   */
  public void payTotalCost(int totalCost) {
    owner.addCredits(totalCost);
  }

  /**
   * Creates a contract on an item object and transfers the cost of the loan.
   *
   * @param lender Member.
   * @param interval Interval.
   * @return Contract.
   */
  public Contract createContract(Member lender, Interval interval) {
    validator.checkNull(lender, "Lender must be specified.");
    validator.checkNull(interval, "Interval must be specified.");

    if (isAvailable(interval)) {
      transferCredits(interval, lender);
      return new Contract(this, interval, lender);
    } else {
      throw new IllegalArgumentException("Item not available during the specified interval.");
    }
  }

  /**
   * Takes care of the transfer of credits associated with a contract/loan.
   */
  private void transferCredits(Interval interval, Member lender) {
    int totalCost = this.getCostPerDay() * interval.getNumberOfDays();
    if (lender.getCredits() < totalCost) {
      throw new IllegalArgumentException("The lender doesn't have enough credits to lend the item.");
    }

    lender.removeCredits(totalCost);
    payTotalCost(totalCost);
  }

  /**
   * Adds a contract to an items contract list.
   *
   * @param contract Contract.
   */
  public void addContract(Contract contract) {
    for (Contract c : contracts) {
      if (contract == c) {
        throw new IllegalArgumentException("The contract is already added to the item.");
      }
    }
    contracts.add(contract);
  }

  /**
   * Returns a copy of the list of contracts on an item.
   *
   * @return ArrayList.
   */
  public ArrayList<Contract> getContracts() {
    ArrayList<Contract> copies = new ArrayList<>();

    for (Contract contract : contracts) {
      copies.add(new Contract(this, contract.getInterval(), contract.getLender()));
    }

    return copies;
  }

  /**
   * Checks if an item object is available for a certain contract/loan.
   *
   * @param interval Interval.
   * @return boolean.
   */
  public boolean isAvailable(Interval interval) {
    for (Contract contract : contracts) {
      if (contract.getInterval().isOverlappingWith(interval)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Creates and adds the contract on an item object.
   *
   * @param lender Member.
   * @param interval Interval.
   */
  public void establishContract(Member lender, Interval interval) {
    Contract contract = createContract(lender, interval);
    addContract(contract);
  }
}
