package controller;

import java.util.ArrayList;
import model.Member;
import model.Registry;
import model.persistence.IPersistence;

public class User {
  private Registry registry;
  private IPersistence persistence;

  public User(IPersistence persistence) {
    registry = new Registry();
    this.persistence = persistence;
  }

  public void startSystem() {
    loadMembersToRegistry();
  }

  private void loadMembersToRegistry() {
    ArrayList<Member> members = persistence.loadData();
    for (Member m : members) {
      registry.addMember(m);
    }
  }
  
}
