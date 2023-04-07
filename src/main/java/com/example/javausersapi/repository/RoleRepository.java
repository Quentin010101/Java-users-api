package com.example.javausersapi.repository;

import com.example.javausersapi.model.ERole;
import com.example.javausersapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
