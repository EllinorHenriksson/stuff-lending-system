package model;

import java.util.ArrayList;

public class Interval {
  private Day startDay;
  private Day endDay;
  ArrayList<Day> days = new ArrayList<>();

  public Interval(Day startDay, Day endDay) {
    setStartDay(startDay);
    setEndDay(endDay);
    createDays();
  }

  private void setStartDay(Day startDay) {
    if (startDay == null) {
      throw new IllegalArgumentException("Start day must be specified.");
    }

    this.startDay = startDay;
  }

  public Day getStartDay() {
    return new Day(startDay.getDayNumber());
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

  public Day getEndDay() {
    return new Day(endDay.getDayNumber());
  }

  private void createDays() {
    for (int i = 0; i < this.getNumberOfDays(); i++) {
      days.add(new Day(this.getStartDay().getDayNumber() + i));
    }
  }

  public ArrayList<Day> getDays() {
    ArrayList<Day> copies = new ArrayList<>();
    for (Day day : days) {
      copies.add(new Day(day.getDayNumber()));
    }
    return copies;
  }

  public int getNumberOfDays() {
    return endDay.getDayNumber() - startDay.getDayNumber();
  }

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
