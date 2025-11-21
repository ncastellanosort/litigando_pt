package com.example.litigando.csv.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.litigando.csv.dto.CSVRequest;
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

  public int processCSV(CSVRequest request) {
    List<Role> roles = roleService.findAll();
    Map<String, Long> roleMap = roles.stream()
                      .collect(Collectors.toMap(r -> r.getNombre().toUpperCase(), Role::getId));

    List<UserDTO> batch = new ArrayList<>();
    int batchSize = request.getbatchSize();
    int processedCount = 0;

      try (CSVReader reader = new CSVReader(new FileReader(request.getPath()))) {
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

          batch.add(new UserDTO(name, email, roleId, date));
          processedCount++;

          logger.info("added user to batch: name={}, email={}, roleId={}, date={}", name, email, roleId, date);

          if (batch.size() >= batchSize) {
            userService.saveAll(batch);
            batch.clear();
          }
        }

        if (!batch.isEmpty()) {
          userService.saveAll(batch);
        }

        return processedCount;
      } catch (IOException e) {
          logger.error("error reading CSV: {}", e.getMessage(), e);
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot read CSV file: " + e.getMessage());
      } catch (CsvException e) {
          logger.error("invalid CSV: {}", e.getMessage(), e);
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid CSV: " + e.getMessage());
      }

  }
}
