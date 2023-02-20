package com.sofka.API_Products.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String username;

    private List roles;
    private String token;

    public AuthResponse(String username, List roles, String token) {
        this.username = username;
        this.roles = roles;
        this.token = token;
    }
}
