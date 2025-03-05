/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteagricultureapi.security.impl;


import com.acl.formaliteagricultureapi.models.Role;
import com.acl.formaliteagricultureapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Jean Paul
 */
@AllArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private final Logger logger = LoggerFactory.getLogger(UserPrincipal.class);
    private Long id;
    private String login;
    private String fullname;
    private String email;
    private String password;

    private User userRole;

    private Set<Role> roles;

    public UserPrincipal(long id, String username, String nom, String prenom, String password, Set<Role> roles) {
        this.id = id;
        this.login = username;
        this.fullname = nom;
        this.email = prenom;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(
                user.getId(),
                user.getLogin(),
                user.getFullname(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("User principal Autorithy : ");
        logger.info("User Role : " + roles);
        userRole = new User();
        userRole.setRoles(roles);
        logger.info("User principal: " + userRole);
        return userRole.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
