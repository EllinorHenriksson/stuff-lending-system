package model.persistence;

import java.util.ArrayList;
import model.Member;

public interface IPersistence {
  ArrayList<Member> loadData();

  void saveData(ArrayList<Member> members);
}
