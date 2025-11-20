package com.example.litigando.logic.dto;

import java.util.Map;

public class FrequencyResponse {
  private Map<Integer, Integer> frequency;

  public FrequencyResponse(Map<Integer, Integer> frequency) {
    this.frequency = frequency;
  }

  public Map<Integer, Integer> getFrequency() {
    return frequency;
  }

  public void setFrequency(Map<Integer, Integer> frequency) {
    this.frequency = frequency;
  }
}
