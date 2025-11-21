package com.example.litigando.csv.model;

public class UserDTO {
  public String name;
  public String email;
  public Long role_id;
  public String fecha_creacion;

  public UserDTO(String name, String email, Long role_id, String fecha_creacion) {
    this.name = name;
    this.email = email;
    this.role_id = role_id;
    this.fecha_creacion = fecha_creacion;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public Long getRole_id() {
	return role_id;
  }

  public void setRole_id(Long role_id) {
	this.role_id = role_id;
  }

  public String getFecha_creacion() {
	return fecha_creacion;
  }

  public void setFecha_creacion(String fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
  }

}
