package com.example.litigando.logic.dto;

public class ParenthesesValidatorResponse {
  private boolean valid;

  public ParenthesesValidatorResponse(boolean valid) {
    this.valid = valid;
  }

  public boolean isValid() {
    return valid;
  }
}
