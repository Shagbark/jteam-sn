package ru.jteam.social.network.service;

import ru.jteam.social.network.dto.UserRegistration;

/**
 * @author protsko on 08.04.18
 */
public interface RegistrationService {

    void register(UserRegistration dto);

}
