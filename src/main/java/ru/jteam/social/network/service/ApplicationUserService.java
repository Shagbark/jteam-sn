package ru.jteam.social.network.service;

import ru.jteam.social.network.dto.ApplicationUser;

/**
 * @author protsko on 19.04.18
 */
public interface ApplicationUserService {

    ApplicationUser findByLogin(String login);

}
