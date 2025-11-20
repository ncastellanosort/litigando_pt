package com.example.litigando.logic.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.litigando.logic.Exercises;
import com.example.litigando.logic.dto.FrequencyRequest;
import com.example.litigando.logic.dto.FrequencyResponse;
import com.example.litigando.logic.dto.ObjectiveSumRequest;
import com.example.litigando.logic.dto.ParenthesesValidatorRequest;

@RestController
@RequestMapping("logic")
public class LogicController {

  @PostMapping("/frequency")
  public ResponseEntity<?> Frequency(@RequestBody FrequencyRequest request) {
    try {
      return ResponseEntity.ok(new FrequencyResponse(
          Exercises.FrequencyNumbers(request.getNumbers())
      ));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping("/objective-sum")
  public ResponseEntity<?> objectiveSum(@RequestBody ObjectiveSumRequest request) {
    try {
      return ResponseEntity.ok(Exercises.objectiveSum(request.getNumbers(), request.getTarget()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @GetMapping("validator")
  public ResponseEntity<?> Validator(@RequestBody ParenthesesValidatorRequest request) {
    try {
      return ResponseEntity.ok(Exercises.parenthesesValidator(request.getText()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }
}
