package model;

public class Day {
  private int dayNumber;

  public Day(int dayNumber) {
    setDayNumber(dayNumber);
  }

  private void setDayNumber(int dayNumber) {
    if (dayNumber < 0) {
      throw new IllegalArgumentException("Day number must not be negative.");
    }

    this.dayNumber = dayNumber;
  }

  public int getDayNumber() {
    return dayNumber;
  }
}
