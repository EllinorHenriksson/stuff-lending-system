package model;

public class DayCounter {
  private Day currentDay;

  public DayCounter() {
    currentDay = new Day(0);
  }

  public void advanceDay() {
    currentDay = new Day(currentDay.getDayNumber() + 1);
  }

  public Day getCurrentDay() {
    return new Day(currentDay.getDayNumber());
  }
}
