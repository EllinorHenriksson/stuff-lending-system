package model;

import java.util.Random;

public class IdGenerator {
  private String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
  private int length;

  public IdGenerator(int length) {
    this.length = length;
  }

  /**
   * Generates a six character long randomized id.
   *
   * @return String with ID.
   */
  public String generateId() {
    Random r = new Random();
    int index;
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < length; i++) {
      index = r.nextInt(characters.length());
      char charHolder = characters.charAt(index);
      str.append(charHolder);
    }
    return str.toString();

  }

}
