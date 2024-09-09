package org.example.securenotes.model;

public record NoteUser(
        String fullName,
        String email,
        String imgUrl
) {
}
