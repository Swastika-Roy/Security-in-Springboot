package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Data

public class SignUpDto {

    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Permission> permissions;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
