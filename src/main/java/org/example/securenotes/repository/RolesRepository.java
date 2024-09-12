package org.example.securenotes.repository;

import org.example.securenotes.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String roleName);
}
