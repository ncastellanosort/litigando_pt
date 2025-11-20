package com.example.litigando.csv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.csv.dto.CSVRequest;
import com.example.litigando.csv.service.CSVService;

@RestController
@RequestMapping("/csv")
public class CSVController {
  @PostMapping
  public ResponseEntity<?> CSV(@RequestBody CSVRequest request) {
      return ResponseEntity.ok(CSVService.ReadCSV(request));
  }
}
