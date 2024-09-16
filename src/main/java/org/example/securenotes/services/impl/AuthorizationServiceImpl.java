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

        if (authorizations == null) {
            // should check if the email exist first if so update the role else create a new one.

            this.authorizationsRepository.save(new Authorizations(email, this.rolesRepository.findByName("ROLE_USER")));
            return "ROLE_USER";
        }

        if(authorizations.getRole() == null){
            // update the user role
            authorizations.setRole(this.rolesRepository.findByName("ROLE_USER"));
            this.authorizationsRepository.save(authorizations);
        }

        return authorizations.getRole().getName();
    }

    @Override
    public boolean isAdmin(String email) {
        return this.getRole(email).equals("ROLE_ADMIN");
    }
}
