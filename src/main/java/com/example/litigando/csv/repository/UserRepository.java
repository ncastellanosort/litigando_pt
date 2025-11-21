package com.example.litigando.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.litigando.csv.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}

