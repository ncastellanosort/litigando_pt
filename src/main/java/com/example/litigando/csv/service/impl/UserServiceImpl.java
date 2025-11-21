package com.example.litigando.csv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.litigando.csv.model.User;
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
}
