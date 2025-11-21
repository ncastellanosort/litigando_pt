package com.example.litigando.csv.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.litigando.csv.model.Role;
import com.example.litigando.csv.model.User;
import com.example.litigando.csv.model.UserDTO;
import com.example.litigando.csv.service.RoleService;
import com.example.litigando.csv.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSV {
  private final RoleService roleService;
  private final UserService userService;

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

          String idStr = row[0];
          String name = row[1];
          String email = row[2];
          String role = row[3];
          Long roleId = roleMap.get(role.toUpperCase());
          String date = row[4];

          // mirar que el rol exista en la tabla ROLES
          // si existe -> insertar el usuario en USUARIOS
          // si no -> mandar a log que no existe

          if (roleId == null) {
            // mandar a log
            System.out.println("invalid rol on row  " + i + ": " + role);
            continue;
          }

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
            System.out.println("\nENTRA");
            System.out.println("usuario " + name);
            System.out.println("role id " + roleId.toString());
            System.out.println("date " + date);
            System.out.println("email " + email + "\n");
            userService.save(new UserDTO(name, email, roleId, date));
          } catch (Exception e) {
            // mandar a log
            System.out.println("error saving in the db" + e.getMessage());
          }  
        }
      } catch (IOException | CsvException e) {
          e.printStackTrace();
    }
  }
}
