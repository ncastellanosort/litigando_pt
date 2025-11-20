package com.example.litigando.csv.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv")
public class CsvController {
  @PostMapping
  public String CSV() {
    return "CSV";
  }
}
