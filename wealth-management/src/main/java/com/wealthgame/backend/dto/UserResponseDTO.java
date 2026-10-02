package com.wealthgame.backend.dto;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public UserResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
