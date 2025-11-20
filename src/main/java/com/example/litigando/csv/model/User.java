package com.example.litigando.csv.model;

public class User {
  public Long id;
  public String name;
  public String email;
  public Long roleId;
  public String fechaCreacion;

  public User(Long id, String name, String email, Long roleId, String fechaCreacion) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.roleId = roleId;
    this.fechaCreacion = fechaCreacion;
  }
}
