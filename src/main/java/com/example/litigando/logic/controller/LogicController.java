package com.example.litigando.logic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.logic.Exercises;
import com.example.litigando.logic.dto.FrequencyRequest;
import com.example.litigando.logic.dto.FrequencyResponse;

@RestController
@RequestMapping("logic")
public class LogicController {

  @PostMapping("/frequency")
  public FrequencyResponse Frequency(@RequestBody FrequencyRequest request) {
    return new FrequencyResponse(
        Exercises.FrequencyNumbers(request.getNumbers())
        );
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
