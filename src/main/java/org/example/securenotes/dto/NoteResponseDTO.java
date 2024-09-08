package org.example.securenotes.dto;

public record NoteResponseDTO(
        long id,
        String title,
        String content
) { }
