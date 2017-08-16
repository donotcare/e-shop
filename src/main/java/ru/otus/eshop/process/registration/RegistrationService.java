package ru.otus.eshop.process.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.process.cart.Cart;
import ru.otus.eshop.process.cart.CartRepository;
import ru.otus.eshop.system.Role;
import ru.otus.eshop.system.UserDetails;
import ru.otus.eshop.system.UserRegistration;
import ru.otus.eshop.system.UserRepository;

import java.util.Collections;
@RequiredArgsConstructor
@Service
@Transactional
public class RegistrationService implements IRegistrationService {
    public final UserRepository userRepository;
    public final CartRepository cartRepository;

    public void registerNewUser(UserRegistration registration) {
        UserDetails user = new UserDetails(registration.getEmail(), Collections.singletonList(Role.ADMIN));
        user.setPassword(registration.getPassword());
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }


}
