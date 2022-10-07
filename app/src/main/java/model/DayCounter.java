package model;

public class DayCounter {
  private Day currentDay;

  public DayCounter() {
    currentDay = new Day(0);
  }

  public void advanceDay(int numberOfDays) {
    currentDay = new Day(currentDay.getDayNumber() + numberOfDays);
  }

  public Day getCurrentDay() {
    return new Day(currentDay.getDayNumber());
  }
}
