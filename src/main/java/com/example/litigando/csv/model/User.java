package com.example.litigando.csv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS", schema = "SYSTEM")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="NOMBRE", nullable=false)
  private String name;

  @Column(name="EMAIL", nullable=false)
  private String email;

  @Column(name="ROLE_ID", nullable=false)
  private Long role_id;

  @Column(name="FECHA_CREACION")
  private String fecha_creacion;

  public User() {};

  public User(Long id, String name, String email, Long role_id, String fecha_creacion) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role_id = role_id;
    this.fecha_creacion = fecha_creacion;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
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
