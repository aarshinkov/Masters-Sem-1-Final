package com.aarshinkov.masters.security;

import com.aarshinkov.masters.entities.RoleEntity;
import com.aarshinkov.masters.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private UserEntity user;
    private Set<GrantedAuthority> authorities;

    public UserPrincipal(UserEntity user, Set<RoleEntity> roles) {
        this.user = user;
        authorities = new HashSet<GrantedAuthority>();
        insertRoles(roles);
    }

    private void insertRoles(Set<RoleEntity> roles) {

        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        if (authorities.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
