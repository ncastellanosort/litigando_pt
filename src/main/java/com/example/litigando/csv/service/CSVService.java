package com.example.litigando.csv.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.litigando.csv.dto.CSVRequest;
import com.example.litigando.csv.model.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVService {
  public static ArrayList<User> ReadCSV(CSVRequest request) {
    String csvFile = request.getPath(); 
    ArrayList<User> users = new ArrayList<>();

      try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
        List<String[]> allData = reader.readAll(); 
        for (int i = 1; i < allData.size(); ++i) {
          String[] row = allData.get(i);

          users.add(
              new User(
                Long.valueOf(row[0]),
                row[1],
                row[2],
                Long.valueOf(row[3]),
                row[4]
              ));
        }
        return users;

      } catch (IOException | CsvException e) {
          e.printStackTrace();
          return null;
    }
  }
}
