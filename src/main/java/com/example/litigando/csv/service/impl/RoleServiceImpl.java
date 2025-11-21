package com.example.litigando.csv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.litigando.csv.model.Role;
import com.example.litigando.csv.repository.RoleRepository;
import com.example.litigando.csv.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repo;

  public RoleServiceImpl(RoleRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<Role> findAll() {
    return repo.findAll();
  }
}
