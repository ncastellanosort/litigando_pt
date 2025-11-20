package com.example.litigando.logic.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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

  @PostMapping("/objective-sum-optimized")
  public ResponseEntity<?> objectiveSumOptimized(@RequestBody ObjectiveSumRequest request) {
    try {
      return ResponseEntity.ok(Exercises.objectiveSumOptimized(request.getNumbers(), request.getTarget()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping("/objective-sum-brute")
  public ResponseEntity<?> objectiveSumBrute(@RequestBody ObjectiveSumRequest request) {
    try {
      return ResponseEntity.ok(Exercises.objectiveSumBrute(request.getNumbers(), request.getTarget()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping("/validator")
  public ResponseEntity<?> Validator(@RequestBody ParenthesesValidatorRequest request) {
    try {
      return ResponseEntity.ok(Exercises.parenthesesValidator(request.getText()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }
}
