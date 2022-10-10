package controller.menus;

import controller.DataFetcher;
import model.Registry;
import view.Console;

/**
 * Represents a menu.
 */
public abstract class Menu {
  protected Console console = new Console();
  protected DataFetcher dataFetcher = new DataFetcher();

  protected Registry registry;

  /**
   * Initializing constructor.
   *
   * @param registry The registry to work with.
   */
  public Menu(Registry registry) {
    this.registry = registry;
  }

  public abstract void doMenu();
}
