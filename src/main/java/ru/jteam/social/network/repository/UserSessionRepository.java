package ru.jteam.social.network.repository;

import ru.jteam.social.network.domain.UserSessionEntity;

/**
 * @author protsko on 11.05.18
 */
public interface UserSessionRepository {

    UserSessionEntity createSession(String login);

    UserSessionEntity findByLogin(String login);

    void remove(String login);

}
