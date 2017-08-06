package ru.otus.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.system.Role;
import ru.otus.eshop.model.system.UserDetails;
import ru.otus.eshop.model.system.UserRegistration;
import ru.otus.eshop.model.system.UserRepository;

import java.util.Collections;

@Service
@Transactional
public class RegistrationService {
    public final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(UserRegistration registration) {
        UserDetails user = new UserDetails(registration.getEmail(), Collections.singletonList(Role.ADMIN));
        user.setPassword(registration.getPassword());
        userRepository.save(user);
    }
}
