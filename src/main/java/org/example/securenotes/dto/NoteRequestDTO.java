package org.example.securenotes.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NoteRequestDTO {
    @NotBlank(message = "Title can't be empty")
    private String title;

    @NotBlank(message = "Content can't be empty")
    private String content;

    public NoteRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
