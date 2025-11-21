package com.example.litigando.csv.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.csv.dto.CSVRequest;
import com.example.litigando.csv.dto.CSVResponse;
import com.example.litigando.csv.utils.CSV;

@RestController
@RequestMapping("/csv")
public class CSVController {
  private final CSV csv = new CSV();

  @PostMapping
  public CSVResponse CSV(@RequestBody CSVRequest request) {
    csv.processCSV(request.getPath());
    return new CSVResponse(csv.getValidUsers());

  }
}
