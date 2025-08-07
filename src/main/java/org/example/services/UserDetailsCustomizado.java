package org.example.services;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class UserDetailsCustomizado implements UserDetails {

    private Long fornecedorId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsCustomizado(Long fornecedorId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.fornecedorId = fornecedorId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    // Implementar os métodos de UserDetails (getAuthorities, getPassword, getUsername, etc)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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
