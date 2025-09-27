package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

  private String name;
  private String email;
//  private String password;
//  private Set<Role> roles;

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
