package ru.otus.eshop.process.registration;

import ru.otus.eshop.system.UserRegistration;

public interface IRegistrationService {
    void registerNewUser(UserRegistration registration);
}
