package com.wealthgame.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequestDTO {
    @NotBlank(message = "Name should not be blank")
    @Size(min = 2, max = 50, message = "The length of name should be min 2 and max 50")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email address")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
