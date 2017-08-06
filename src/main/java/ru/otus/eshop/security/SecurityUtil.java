package ru.otus.eshop.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class SecurityUtil {
    public static void runAs(String username, String password, String... roles) {

        Assert.notNull(username, "Username must not be null!");
        Assert.notNull(password, "Password must not be null!");

        SecurityContextHolder.getContext().setAuthentication(auth(username, password, roles));
    }

    private static Authentication auth(String login, String password, String[] roles) {
        return new UsernamePasswordAuthenticationToken(login, password, AuthorityUtils.createAuthorityList(roles));
    }
}
