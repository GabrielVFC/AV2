package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Enum para roles como ROLE_USER ou ROLE_ADMIN
}