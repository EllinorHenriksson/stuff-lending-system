package model;

/**
 * Class for a day object.
 */
public class Day {
  private int dayNumber;

  /**
   * Constructor for day object.
   *
   * @param dayNumber the day number to set on the day object.
   */
  public Day(int dayNumber) {
    setDayNumber(dayNumber);
  }

  /**
   * Sets the day number.
   *
   * @param dayNumber the number to set.
   */
  private void setDayNumber(int dayNumber) {
    if (dayNumber < 0) {
      throw new IllegalArgumentException("Day number must not be negative.");
    }

    this.dayNumber = dayNumber;
  }

  /**
   * Returns the number of a day oibject.
   *
   * @return int.
   */
  public int getDayNumber() {
    return dayNumber;
  }
}
