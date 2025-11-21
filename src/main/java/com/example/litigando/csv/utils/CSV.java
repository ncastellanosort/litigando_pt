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

  // logger con el nombre de la clase
  private static final Logger logger = LoggerFactory.getLogger(CSV.class);

  public CSV(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  public int processCSV(CSVRequest request) {
    // traer todos los roles de la base de datos
    List<Role> roles = roleService.findAll();

    // guardamos el nombre del rol con su id de la base de datos
    Map<String, Long> roleMap = roles.stream()
                      .collect(Collectors.toMap(r -> r.getNombre().toUpperCase(), Role::getId));

    // creamos el batch para la insercion
    List<UserDTO> batch = new ArrayList<>();
    int batchSize = request.getbatchSize();
    int processedCount = 0;

    // abrir el CSV
      try (CSVReader reader = new CSVReader(new FileReader(request.getPath()))) {
        // aca tenemos todos los registros del CSV
        List<String[]> allData = reader.readAll(); 
        // empezar desde 1, ya que el primer registro es la definicion de los datos
        for (int i = 1; i < allData.size(); ++i) {
          // aca extraemos cada uno de los registros
          String[] row = allData.get(i);

          // obtenemos cada uno de los datos por posicion
          String name = row[1];
          String email = row[2];
          String role = row[3];

          // gracias al map que creamos, vamos a traer el value del roleId en upper case
          Long roleId = roleMap.get(role.toUpperCase());
          String date = row[4];

          // validacion de si no existe el rol
          if (roleId == null) {
            logger.warn("invalid role on row {}: {}", i, role);
            continue;
          }

          // validacion si el nombre no existe
          if (name == null || name.isBlank()) {
            logger.warn("invalid name on row {}", i);
            continue;
          }

          // validacion para checkear el email
          if (!ConstraintsChecker.checkEmail(email)) {
            logger.warn("invalid email on row {}: {}", i, email);
            continue;
          }

          // agregamos a los batches un nuevo registro
          batch.add(new UserDTO(name, email, roleId, date));
          processedCount++; // aumentamos el contador

          logger.info("added user to batch: name={}, email={}, roleId={}, date={}", name, email, roleId, date);

          // si el tamano de la lista del batch size es mayor o igual al batch Size ingresado, realizamos el save all
          if (batch.size() >= batchSize) {
            userService.saveAll(batch);
            batch.clear();
          }
        }

        // si falta algun registro, tambien realiamos el saveAll
        if (!batch.isEmpty()) {
          userService.saveAll(batch);
        }

        return processedCount; // devolvemos el processed count
      } catch (IOException e) {
          logger.error("error reading CSV: {}", e.getMessage(), e); // excepcion leyendo el csv
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot read CSV file: " + e.getMessage());
      } catch (CsvException e) {
          logger.error("invalid CSV: {}", e.getMessage(), e); // excepcion de un csv invalido
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid CSV: " + e.getMessage());
      }

  }
}
