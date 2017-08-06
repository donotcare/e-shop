package ru.otus.eshop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.eshop.model.system.CurrentUser;
import ru.otus.eshop.model.system.UserRepository;

@Service
public class CurrentUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CurrentUser(userRepository.findByUsername(username));
    }
}
