package model.persistence;

import model.Registry;

public interface IfPersistence {
  public void loadDataToRegistry(Registry registry);

  public void saveDataFromRegistry(Registry registry);
}
