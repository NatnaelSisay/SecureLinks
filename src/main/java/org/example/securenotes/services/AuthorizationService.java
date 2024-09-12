package org.example.securenotes.services;

public interface AuthorizationService {
    public String getRole(String email);
    public boolean isAdmin(String email);
}
