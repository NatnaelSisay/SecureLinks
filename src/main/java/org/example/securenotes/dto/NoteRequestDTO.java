package org.example.securenotes.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NoteRequestDTO {
    private String title;
    private String content;

    public NoteRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
