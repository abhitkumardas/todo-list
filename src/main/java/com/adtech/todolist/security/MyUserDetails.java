package com.adtech.todolist.security;

import com.adtech.todolist.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ferozk
 */
public class MyUserDetails implements UserDetails {

    private User user;

    MyUserDetails(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        this.user.getGrandsList().forEach(g->{
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(g);
            grantedAuthorities.add(grantedAuthority);
        });
        this.user.getRoleList().forEach(r->{
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("ROLE_"+r);
            grantedAuthorities.add(grantedAuthority);
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
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
