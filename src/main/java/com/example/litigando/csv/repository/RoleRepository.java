package com.example.litigando.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.litigando.csv.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
