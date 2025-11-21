package com.example.litigando.csv.model;

public class RoleDTO {
  private String nombre;

  public RoleDTO() {}

  public RoleDTO(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
