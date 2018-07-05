package ru.jteam.social.network.service;

import ru.jteam.social.network.dto.UserSession;

/**
 * @author protsko on 02.07.18
 */
public interface UserSessionService {

    UserSession createSession(String login);

    UserSession findByLogin(String login);

    boolean isExpired(String login);

    void removeSession(String login);

}
