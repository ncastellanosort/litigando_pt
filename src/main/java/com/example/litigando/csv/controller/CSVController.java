package com.example.litigando.csv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.litigando.csv.dto.CSVRequest;
import com.example.litigando.csv.dto.CSVResponse;
import com.example.litigando.csv.service.RoleService;
import com.example.litigando.csv.service.UserService;
import com.example.litigando.csv.utils.CSV;
import com.example.litigando.csv.model.Role;

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
  public boolean CSV(@RequestBody CSVRequest request) {
    csv.processCSV(request.getPath());
    return true;
  }

  @GetMapping
  public List<Role> getRoles() {
    return this.roleService.findAll();
  }
}
