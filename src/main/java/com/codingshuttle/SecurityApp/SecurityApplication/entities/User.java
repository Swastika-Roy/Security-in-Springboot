package com.codingshuttle.SecurityApp.SecurityApplication.entities;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.codingshuttle.SecurityApp.SecurityApplication.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

//     @ElementCollection(fetch = FetchType.EAGER)
//     @Enumerated(EnumType.STRING)
//     private Set<Role> roles;
//
//
//     @ElementCollection(fetch = FetchType.EAGER)
//     @Enumerated(EnumType.STRING)
//     private Set<Permission> permissions;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = PermissionMapping.
                            grantedAuthoritiesForRole(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );

        return authorities;
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    //    public void setPassword(String password) {
//        this.password = password;
//    }

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//    }

//    public Set<Role> getRoles() {
//        return roles;
//    }

//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }




//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
}
