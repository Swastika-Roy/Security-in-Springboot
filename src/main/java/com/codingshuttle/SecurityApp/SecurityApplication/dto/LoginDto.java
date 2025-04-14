package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data


public class LoginDto {
    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
