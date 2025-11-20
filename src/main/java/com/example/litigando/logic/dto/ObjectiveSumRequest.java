package com.example.litigando.logic.dto;

public class ObjectiveSumRequest {
  private int[] numbers;
  private int target;

  public int[] getNumbers() {
    return numbers;
  }

  public void setNumbers(int[] numbers) {
    this.numbers = numbers;
  }

  public int getTarget() {
    return target;
  }

  public void setTarget(int target) {
    this.target = target;
  }
}

