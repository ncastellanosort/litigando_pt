package com.example.litigando.csv.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.csv.dto.CSVRequest;
import com.example.litigando.csv.service.RoleService;
import com.example.litigando.csv.service.UserService;
import com.example.litigando.csv.utils.CSV;

@RestController
@RequestMapping("/csv")
public class CSVController {
  private final CSV csv;
  private final RoleService roleService;
  private final UserService userService;

  public CSVController(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
    this.csv = new CSV(roleService, userService);
  } 

  @PostMapping
  public ResponseEntity<?> CSV(@RequestBody CSVRequest request) {
    try {
        int processed = csv.processCSV(request);
        return ResponseEntity.ok(Map.of(
            "message", "CSV processed successfully",
            "records_processed", processed
        ));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of(
            "message", "failed to process CSV",
            "error", e.getMessage()
        ));
    }
  }
}
