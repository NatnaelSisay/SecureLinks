package org.example.securenotes.repository;

import org.example.securenotes.model.Authorizations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationsRepository extends JpaRepository<Authorizations, Long> {
    public Optional<Authorizations> findByEmail(String email);
}
