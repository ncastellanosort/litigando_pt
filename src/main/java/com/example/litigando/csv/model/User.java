package com.example.litigando.csv.model;

public class User {
  public Long id;
  public String name;
  public String email;
  public String role;
  public String fechaCreacion;

  public User(Long id, String name, String email, String role, String fechaCreacion) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.fechaCreacion = fechaCreacion;
  }
}
