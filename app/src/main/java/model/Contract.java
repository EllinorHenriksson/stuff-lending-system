package model;

/**
 * Class for a contract object.
 */
public class Contract {
  private Item item;
  private Interval interval;
  private Member lender;

  /**
   * Constructor for contract object.
   *
   * @param item the item to loan.
   * @param interval the interval for the loan.
   * @param lender the member object that loans the item.
   */
  public Contract(Item item, Interval interval, Member lender) {
    setItem(item);
    setInterval(interval);
    setLender(lender);
  }

  /**
   * Sets the lender from a member object.
   *
   * @param lender the member object to set.
   */
  private void setLender(Member lender) {
    if (lender == null) {
      throw new IllegalArgumentException("Lender must be specified.");
    }

    this.lender = lender;
  }

  /**
   * Returns a copy of the lender on a contract object.
   *
   * @return Member.
   */
  public Member getLender() {
    return new Member(lender.getName(), lender.getEmail(), lender.getPhoneNumber(), lender.getId(), lender.getCreationDay());
  }

  /**
   * Sets the interval of the loan.
   *
   * @param interval the time interval.
   */
  private void setInterval(Interval interval) {
    if (interval == null) {
      throw new IllegalArgumentException("Interval must be specified.");
    }

    this.interval = interval;
  }

  /**
   * Returns a copy of the interval on a contract object.
   *
   * @return Interval.
   */
  public Interval getInterval() {
    return new Interval(interval.getStartDay(), interval.getEndDay());
  }

  /**
   * Sets the item on the contract object.
   *
   * @param item item.
   */
  private void setItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item must be specified.");
    }
  
    this.item = item;
  }

  /**
   * Returns the total cost of the loan.
   *
   * @return int.
   */
  public int getTotalCost() {
    if (lender.getId().equals(item.getOwner().getId())) {
      return 0;
    } else {
      return item.getCostPerDay() * interval.getNumberOfDays();
    }
  }
}
