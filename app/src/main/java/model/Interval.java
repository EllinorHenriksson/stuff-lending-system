package model;

public class Interval {
  private Day startDay;
  private Day endDay;

  public Interval(Day startDay, Day endDay) {
    setStartDay(startDay);
    setEndDay(endDay);
  }

  private void setStartDay(Day startDay) {
    if (startDay == null) {
      throw new IllegalArgumentException("Start day must be specified.");
    }

    this.startDay = startDay;
  }

  private void setEndDay(Day endDay) {
    if (endDay == null) {
      throw new IllegalArgumentException("End day must be specified.");
    }

    if (endDay.getDayNumber() <= startDay.getDayNumber()) {
      throw new IllegalArgumentException("End day must be later than start day.");
    }

    this.endDay = endDay;
  }

  public int getNumberOfDays() {
    return endDay.getDayNumber() - startDay.getDayNumber();
  }
}
