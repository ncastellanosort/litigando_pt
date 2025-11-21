package com.example.litigando.csv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.litigando.csv.model.User;
import com.example.litigando.csv.model.UserDTO;
import com.example.litigando.csv.repository.UserRepository;
import com.example.litigando.csv.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repo;

  public UserServiceImpl(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<User> findAll() {
    return repo.findAll();
  }

  @Override
  public User save(UserDTO dto) {
    User user = new User(
        null, 
        dto.getName(),
        dto.getEmail(),
        dto.getRole_id(),
        dto.getFecha_creacion()
    );
    return repo.save(user);
  }

  @Override
    public List<User> saveAll(List<UserDTO> dtos) {
      List<User> users = dtos.stream()
        .map(dto -> new User(
            null,
            dto.getName(),
            dto.getEmail(),
            dto.getRole_id(),
            dto.getFecha_creacion()
        ))
        .toList();

       return repo.saveAll(users);
   }
}
