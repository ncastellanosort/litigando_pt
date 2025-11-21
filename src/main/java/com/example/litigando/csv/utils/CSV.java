package com.example.litigando.csv.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.litigando.csv.model.Role;
import com.example.litigando.csv.model.UserDTO;
import com.example.litigando.csv.service.RoleService;
import com.example.litigando.csv.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSV {
  private final RoleService roleService;
  private final UserService userService;

  // logger
  private static final Logger logger = LoggerFactory.getLogger(CSV.class);

  public CSV(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  public void processCSV(String path) {
    List<Role> roles = roleService.findAll();
    Map<String, Long> roleMap = roles.stream()
                      .collect(Collectors.toMap(r -> r.getNombre().toUpperCase(), Role::getId));

      try (CSVReader reader = new CSVReader(new FileReader(path))) {
        List<String[]> allData = reader.readAll(); 
        for (int i = 1; i < allData.size(); ++i) {
          String[] row = allData.get(i);

          String name = row[1];
          String email = row[2];
          String role = row[3];
          Long roleId = roleMap.get(role.toUpperCase());
          String date = row[4];

          if (roleId == null) {
            logger.warn("invalid role on row {}: {}", i, role);
            continue;
          }

          if (name == null || name.isBlank()) {
            logger.warn("invalid name on row {}", i);
            continue;
          }

          if (!ConstraintsChecker.checkEmail(email)) {
            logger.warn("invalid email on row {}: {}", i, email);
            continue;
          }

           try {
             logger.info("processing user: name={}, email={}, roleId={}, date={}", name, email, roleId, date);
             userService.save(new UserDTO(name, email, roleId, date));
          } catch (Exception e) {
             logger.error("error saving user in db on row {}: {}", i, e.getMessage(), e);
          }  
        }
      } catch (IOException | CsvException e) {
          e.printStackTrace();
    }
  }
}
