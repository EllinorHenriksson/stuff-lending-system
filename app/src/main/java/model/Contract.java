package model;

public class Contract {
  private Item item;
  private Interval interval;
  private Member lender;

  public Contract(Item item, Interval interval, Member lender) {
    setItem(item);
    setInterval(interval);
    setLender(lender);
  }

  private void setLender(Member lender) {
    if (lender == null) {
      throw new IllegalArgumentException("Lender must be specified.");
    }

    this.lender = lender;
  }

  public Member getLender() {
    return new Member(lender.getName(), lender.getEmail(), lender.getPhoneNumber(), lender.getId(), lender.getCreationDay());
  }

  private void setInterval(Interval interval) {
    if (interval == null) {
      throw new IllegalArgumentException("Interval must be specified.");
    }

    this.interval = interval;
  }

  public Interval getInterval() {
    return new Interval(interval.getStartDay(), interval.getEndDay());
  }

  private void setItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item must be specified.");
    }

    this.item = item;
  }

  public int getTotalCost() {
    if (lender.getId().equals(item.getOwner().getId())) {
      return 0;
    } else {
      return item.getCostPerDay() * interval.getNumberOfDays();
    }
  }
}
