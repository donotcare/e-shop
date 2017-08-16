package ru.otus.eshop.system;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CurrentUser extends User {
    private UserDetails user;

    public CurrentUser(UserDetails user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList());
        this.user = user;
    }

    public UserDetails getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public List<Role> getRoles() {
        return user.getRoles();
    }

}
