package com.microservicetest.authservice.repositories;

import com.microservicetest.authservice.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
