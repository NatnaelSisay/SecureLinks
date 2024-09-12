package org.example.securenotes.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.securenotes.model.Authorizations;
import org.example.securenotes.repository.AuthorizationsRepository;
import org.example.securenotes.repository.RolesRepository;
import org.example.securenotes.services.AuthorizationService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
    final AuthorizationsRepository authorizationsRepository;
    final RolesRepository rolesRepository;

    @Override
    public String getRole(String email) {
        Authorizations authorizations = this.authorizationsRepository.findByEmail(email).orElse(null);

        if (authorizations == null || authorizations.getRole() == null) {
            this.authorizationsRepository.save(new Authorizations(email, this.rolesRepository.findByName("ROLE_USER")));
            return "ROLE_USER";
        }

        return authorizations.getRole().getName();
    }

    @Override
    public boolean isAdmin(String email) {
        return this.getRole(email).equals("ROLE_ADMIN");
    }
}
