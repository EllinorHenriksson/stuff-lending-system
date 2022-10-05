package model.persistence;

import java.util.ArrayList;
import model.Member;

public interface IfPersistence {
  ArrayList<Member> loadData();

  void saveData(ArrayList<Member> members);
}
