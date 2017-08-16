package ru.otus.eshop.system;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
@RequiredArgsConstructor
@Getter
public class UserRegistration {
    @NotEmpty
    private final String email;

    @NotEmpty
    private final String password;

    @NotEmpty
    private final String passwordRepeated;

    @NotNull
    private final Role role = Role.USER;
}
