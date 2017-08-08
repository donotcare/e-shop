package ru.otus.eshop.service;

import ru.otus.eshop.model.system.UserRegistration;

public interface IRegistrationService {
    void registerNewUser(UserRegistration registration);
}
