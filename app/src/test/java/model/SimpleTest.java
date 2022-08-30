package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class SimpleTest {

  @Test
  public void testMessageLengthGreaterThanZero() {
    Simple sut = new Simple();

    assertTrue(sut.getMessage().length() > 0, "Message length should be greater that zero");
  }
  
}
