package com.example.litigando.logic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logic")
public class LogicController {

  @GetMapping("frequency")
  public String Frequency() {
    return "Frecuencia";
  }

  @GetMapping("objective-sum")
  public String ObjectiveSum() {
    return "Suma objetivo";
  }

  @GetMapping("validator")
  public String Validator() {
    return "Validar parentesis";
  }
}
