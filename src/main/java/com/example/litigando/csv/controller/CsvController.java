package com.example.litigando.csv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.csv.dto.CSVRequest;

@RestController
@RequestMapping("/csv")
public class CsvController {
  @PostMapping
  public ResponseEntity<?> CSV(@RequestBody CSVRequest request) {
      return ResponseEntity.ok(new String("CSV"));
  }
}
