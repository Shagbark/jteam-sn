package ru.jteam.social.network.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jteam.social.network.domain.ApplicationUserEntity;
import ru.jteam.social.network.dto.UserRegistration;
import ru.jteam.social.network.exception.AccountExistsException;
import ru.jteam.social.network.repository.ApplicationUserRepository;
import ru.jteam.social.network.service.PasswordService;
import ru.jteam.social.network.service.RegistrationService;

import java.util.Objects;

/**
 * @author protsko on 08.04.18
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserRepository userRepository;
    private final PasswordService passwordService;

    @Autowired
    public RegistrationServiceImpl(ApplicationUserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public void register(UserRegistration userRegistration) {
        Objects.requireNonNull(userRegistration, "Object must be non null!");

        ApplicationUserEntity user = find(userRegistration.getLogin());
        if (user != null) {
            throw new AccountExistsException(userRegistration.getLogin());
        }

        String encodedPassword = passwordService.encryptPassword(userRegistration.getPassword());
        userRepository.createNewAccount(userRegistration.getLogin(), userRegistration.getName(),
                userRegistration.getLastName(), userRegistration.getEmail(), encodedPassword);
    }

    private ApplicationUserEntity find(String login) {
        if (login == null || login.trim().isEmpty()) {
            return null;
        }
        return userRepository.findByLogin(login);
    }

}
