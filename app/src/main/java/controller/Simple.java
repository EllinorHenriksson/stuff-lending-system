package controller;

/**
 * This is just an example contoller class. You should remove it.
 */
public class Simple {

  /**
   * Gets a message from the model and tells the view to show it.

   * @param m The model to get message from.
   * @param v The view used to show the message.
   */
  public void doSomethingSimple(model.Simple m, view.Simple v) {
    v.showMessage(m.getMessage());
  }

}
