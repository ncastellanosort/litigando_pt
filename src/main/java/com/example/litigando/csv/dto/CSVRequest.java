package com.example.litigando.csv.dto;

public class CSVRequest {
  private String path;
  private int batchSize;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getbatchSize() {
    return batchSize;
  }

  public void setbatchSize(int batchSize) {
    this.batchSize = batchSize;
  }
}
