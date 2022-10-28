package model.persistence;

import model.Registry;

/**
 * Interface for persistence clasess.
 */
public interface IfPersistence {

  /**
   * Loads data to a registry object.
   *
   * @param registry Registry.
   */
  public void loadDataToRegistry(Registry registry);

  /**
   * Saves data to a registry object.
   *
   * @param registry Registry.
   */
  public void saveDataFromRegistry(Registry registry);
}
