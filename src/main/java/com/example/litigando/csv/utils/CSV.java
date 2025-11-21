package com.example.litigando.csv.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.litigando.csv.model.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSV {
  private ArrayList<User> validUsers = new ArrayList<>();

  public ArrayList<User> getValidUsers() {
    return validUsers;
  }

  public void processCSV(String path) {
    validUsers.clear();

      try (CSVReader reader = new CSVReader(new FileReader(path))) {
        List<String[]> allData = reader.readAll(); 
        for (int i = 1; i < allData.size(); ++i) {
          String[] row = allData.get(i);

          String idStr = row[0];
          String name = row[1];
          String email = row[2];
          String role = row[3];
          String date = row[4];

          if (name == null || name.isBlank()) {
            // mandar a log
            System.out.println("invalid name on row " + i);
            continue;
          }

          if (!ConstraintsChecker.checkEmail(email)) {
            // mandar a log
            System.out.println("invalid email on row  " + i + ": " + email);
            continue;
          }

           try {
            Long id = Long.valueOf(idStr);
            validUsers.add(new User(id, name, email, role, date));
          } catch (NumberFormatException e) {
            // mandar a log
            System.out.println("invalid id on row " + i + ": " + idStr);
          }  
        }
      } catch (IOException | CsvException e) {
          e.printStackTrace();
    }
  }
}
