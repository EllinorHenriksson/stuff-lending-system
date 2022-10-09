package model;

import java.util.ArrayList;

/**
 * Class for interval object.
 */
public class Interval {
  private Day startDay;
  private Day endDay;
  ArrayList<Day> days = new ArrayList<>();

  /**
   * Constructor for interval object that sets start and end day.
   *
   * @param startDay Day object; start of the interval.
   * @param endDay Day object; end of the interval.
   */
  public Interval(Day startDay, Day endDay) {
    setStartDay(startDay);
    setEndDay(endDay);
    createDays();
  }

  /**
   * Sets the start day of the interval object.
   *
   * @param startDay Day.
   */
  private void setStartDay(Day startDay) {
    if (startDay == null) {
      throw new IllegalArgumentException("Start day must be specified.");
    }

    this.startDay = startDay;
  }

  /**
   * Returns the start day of interval object.
   *
   * @return Day.
   */
  public Day getStartDay() {
    return new Day(startDay.getDayNumber());
  }

  /**
   * Sets the end day of the interval object.
   *
   * @param endDay Day.
   */
  private void setEndDay(Day endDay) {
    if (endDay == null) {
      throw new IllegalArgumentException("End day must be specified.");
    }

    if (endDay.getDayNumber() < startDay.getDayNumber()) {
      throw new IllegalArgumentException("End day must not be earlier than start day.");
    }

    this.endDay = endDay;
  }

  /**
   * Returns the end day of interval object.
   *
   * @return Day.
   */
  public Day getEndDay() {
    return new Day(endDay.getDayNumber());
  }

  /**
   * Creates an array of all the days in the interval object.
   */
  private void createDays() {
    for (int i = 0; i < this.getNumberOfDays(); i++) {
      days.add(new Day(this.getStartDay().getDayNumber() + i));
    }
  }

  /**
   * Returns the days of the interval object.
   *
   * @return ArrayList.
   */
  public ArrayList<Day> getDays() {
    ArrayList<Day> copies = new ArrayList<>();
    for (Day day : days) {
      copies.add(new Day(day.getDayNumber()));
    }
    return copies;
  }

  /**
   * Returns the number of days of the interval object.
   *
   * @return int.
   */
  public int getNumberOfDays() {
    return endDay.getDayNumber() - startDay.getDayNumber() + 1;
  }

  /**
   * Checks if the nterval object overlaps with another interval object.
   *
   * @param interval the interval to compare this with.
   * @return boolean.
   */
  public boolean isOverlappingWith(Interval interval) {
    boolean isOverlappingWith = false;

    for (int i = 0; i < days.size(); i++) {
      for (int j = 0; j < interval.getDays().size(); j++) {
        if (days.get(i).getDayNumber() == interval.getDays().get(j).getDayNumber()) {
          isOverlappingWith = true;
          return isOverlappingWith;
        }
      }
    }
    return isOverlappingWith;
  }
}
