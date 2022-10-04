import controller.User;
import model.persistence.Persistence;

public class App {
  public static void main(String[] args) {

    try {
      User user = new User(new Persistence());
      user.startSystem();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(e.getStackTrace());
    }
  }
}
