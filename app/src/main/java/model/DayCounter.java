package model;

/**
 * Class for a dayCounter object.
 */
public class DayCounter {
  private Day currentDay;

  /**
   * Constructor for dayCounter object, sets the current day to zero representing starting day.
   */
  public DayCounter() {
    currentDay = new Day(0);
  }

  /**
   * Advances the time of the system by adding day(s) to the current day parameter.
   *
   * @param numberOfDays The number of days to advance.
   */
  public void advanceDay(int numberOfDays) {
    currentDay = new Day(currentDay.getDayNumber() + numberOfDays);
  }

  /**
   * Returns a copy the current day.
   *
   * @return Day.
   */
  public Day getCurrentDay() {
    return new Day(currentDay.getDayNumber());
  }
}
