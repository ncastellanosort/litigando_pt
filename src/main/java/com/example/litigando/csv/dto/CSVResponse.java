package com.example.litigando.csv.dto;

import java.util.ArrayList;
import com.example.litigando.csv.model.User;

public class CSVResponse {
  private ArrayList<User> users;

  public CSVResponse(ArrayList<User> users) {
    this.users = users;
  }

  public ArrayList<User> getUsers() {
    return users;
  }
}

