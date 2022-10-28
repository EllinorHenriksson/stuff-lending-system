package controller;

import model.persistence.Persistence;

/**
 * Represents the application.
 */
public class App {

  /**
   * The main method of the application.
   */
  public static void main(String[] args) {
    try {
      User user = new User(new Persistence());
      user.startProgram();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
