package com.sofka.API_Products.model;

import lombok.Data;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class UserDTO {


    private String email;

    private String password;

    private Set<String> rols = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password) && Objects.equals(rols, userDTO.rols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, rols);
    }
}
