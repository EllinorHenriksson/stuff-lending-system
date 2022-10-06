package model;

import java.util.regex.Pattern;

public class Validator {
  private int nameMinLength = 3;
  private int nameMaxLength = 50;
  private int descriptionMinLength = 3;
  private int descriptionMaxLength = 200;

  public void validateName(String name) {
    checkNull(name, "Name must be specified.");
    checkNameLength(name);
  }

  public void validateEmail(String email) {
    String errorMessage = "Email must be specified.";
    checkNull(email, errorMessage);
    checkStringLength(email, errorMessage);
    checkEmailPattern(email);
  }

  public void validatePhoneNumber(String phoneNumber) {
    String errorMessage = "Phone number must be specified.";
    checkNull(phoneNumber, errorMessage);
    checkStringLength(phoneNumber, errorMessage);
    checkPhoneNumberPattern(phoneNumber);
  }

  public void validateId(String id) {
    String errorMessage = "Id must be specified.";
    checkNull(id, errorMessage);
    checkStringLength(id, errorMessage);
    checkIdPattern(id);
  }
  
  public void validateDescription(String description) {
    checkNull(description, "Description must be specified.");
    checkDescriptionLength(description);
  }

  private void checkIdPattern(String id) {
    if (!Pattern.matches("^[a-z, A-Z, 0-9]{6}", id)) {
      throw new IllegalArgumentException("Id must consist of 6 alphanumerical characters.");
    }
  }

  public void validateCredits(int credits) {
    if (credits <= 0) {
      throw new IllegalArgumentException("Credits must be a positive number.");
    }
  }

  public void checkNull(Object input, String errorMessage) {
    if (input == null) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  private void checkStringLength(String input, String errorMessage) {
    if (input.length() == 0) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  private void checkNameLength(String name) {
    checkMinLength(name, nameMinLength);
    checkMaxLength(name, nameMaxLength);
  }

  private void checkDescriptionLength(String description) {
    checkMinLength(description, descriptionMinLength);
    checkMaxLength(description, descriptionMaxLength);
  }

  private void checkMinLength(String input, int minLength) {
    if (input.length() < minLength) {
      throw new IllegalArgumentException("Input must be over " + minLength + " characters long.");
    }
  }

  private void checkMaxLength(String input, int maxLength) {
    if (input.length() > maxLength) {
      throw new IllegalArgumentException("Input must not be over " + maxLength + " characters long.");
    }
  }

  private void checkEmailPattern(String email) {
    if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
      throw new IllegalArgumentException("Email must be written in the correct format.");
    }
  }

  private void checkPhoneNumberPattern(String phoneNumber) {
    if (!Pattern.matches("[0-9]+", phoneNumber)) {
      throw new IllegalArgumentException("Phone number must only consist of numbers.");
    }    
  }
}