package ru.jteam.social.network.service;

import ru.jteam.social.network.dto.UserSession;

/**
 * @author protsko on 02.07.18
 */
public interface UserSessionService {

    // TODO: think about recreate session (session expired, user login again, but record exists)
    UserSession createSession(String login);

    UserSession findByLogin(String login);

    boolean isExpired(String login);

    void removeSession(String login);

}
