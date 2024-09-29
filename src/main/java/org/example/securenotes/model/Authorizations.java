package org.example.securenotes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Authorizations {
    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
