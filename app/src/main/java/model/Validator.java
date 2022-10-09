package model;

import java.util.regex.Pattern;

/**
 * Class for a validator object.
 */
public class Validator {
  private int nameMinLength = 3;
  private int nameMaxLength = 50;
  private int descriptionMinLength = 3;
  private int descriptionMaxLength = 200;

  /**
   * Validates a name.
   *
   * @param name String.
   */
  public void validateName(String name) {
    checkNull(name, "Name must be specified.");
    checkNameLength(name);
  }

  /**
   * Validates an email.
   *
   * @param email String.
   */
  public void validateEmail(String email) {
    String errorMessage = "Email must be specified.";
    checkNull(email, errorMessage);
    checkStringLength(email, errorMessage);
    checkEmailPattern(email);
  }

  /**
   * Validates a phone number.
   *
   * @param phoneNumber String.
   */
  public void validatePhoneNumber(String phoneNumber) {
    String errorMessage = "Phone number must be specified.";
    checkNull(phoneNumber, errorMessage);
    checkStringLength(phoneNumber, errorMessage);
    checkPhoneNumberPattern(phoneNumber);
  }

  /**
   * validates an id (from user).
   *
   * @param id String.
   */
  public void validateId(String id) {
    String errorMessage = "Id must be specified.";
    checkNull(id, errorMessage);
    checkStringLength(id, errorMessage);
    checkIdPattern(id);
  }
  
  /**
   * validates a description of an item.
   *
   * @param description String.
   */
  public void validateDescription(String description) {
    checkNull(description, "Description must be specified.");
    checkDescriptionLength(description);
  }

  /**
   * Checks the format of the id.
   *
   * @param id String.
   */
  private void checkIdPattern(String id) {
    if (!Pattern.matches("^[a-z, A-Z, 0-9]{6}", id)) {
      throw new IllegalArgumentException("Id must consist of 6 alphanumerical characters.");
    }
  }

  /**
   * Checks if a number is positive.
   *
   * @param input int.
   */
  public void checkPositive(int input) {
    if (input <= 0) {
      throw new IllegalArgumentException("Input must be a positive number.");
    }
  }

  /**
   * Checks if an input exists.
   *
   * @param input Object.
   * @param errorMessage String.
   */
  public void checkNull(Object input, String errorMessage) {
    if (input == null) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  /**
   * Checks the length of a string.
   *
   * @param input String.
   * @param errorMessage String.
   */
  private void checkStringLength(String input, String errorMessage) {
    if (input.length() == 0) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  /**
   * Checs the length of a name.
   *
   * @param name String.
   */
  private void checkNameLength(String name) {
    checkMinLength(name, nameMinLength);
    checkMaxLength(name, nameMaxLength);
  }

  /**
   * Checks the length of a description.
   *
   * @param description String.
   */
  private void checkDescriptionLength(String description) {
    checkMinLength(description, descriptionMinLength);
    checkMaxLength(description, descriptionMaxLength);
  }

  /**
   * Check min length of a string.
   *
   * @param input String.
   * @param minLength int.
   */
  private void checkMinLength(String input, int minLength) {
    if (input.length() < minLength) {
      throw new IllegalArgumentException("Input must be over " + minLength + " characters long.");
    }
  }

  /**
   * Checks max lenght of a String.
   *
   * @param input String.
   * @param maxLength int.
   */
  private void checkMaxLength(String input, int maxLength) {
    if (input.length() > maxLength) {
      throw new IllegalArgumentException("Input must not be over " + maxLength + " characters long.");
    }
  }

  /**
   * Checks the format of an email.
   *
   * @param email String.
   */
  private void checkEmailPattern(String email) {
    if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
      throw new IllegalArgumentException("Email must be written in the correct format.");
    }
  }

  /**
   * Checks the format of a phone number.
   *
   * @param phoneNumber String.
   */
  private void checkPhoneNumberPattern(String phoneNumber) {
    if (!Pattern.matches("[0-9]+", phoneNumber)) {
      throw new IllegalArgumentException("Phone number must only consist of numbers.");
    }    
  }
}