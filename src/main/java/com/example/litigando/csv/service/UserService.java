package com.example.litigando.csv.service;

import java.util.List;

import com.example.litigando.csv.model.UserDTO;
import com.example.litigando.csv.model.User;

public interface UserService {
  List<User> findAll();
  User save(UserDTO dto);
  // numero de usuarios por rol, de mayor a menor
  // usuarios que no tienen rol asignado
  // actualizar email de un usuario por id
  // eliminar usuarios duplicados por email, dejando solo el mas reciente
}

