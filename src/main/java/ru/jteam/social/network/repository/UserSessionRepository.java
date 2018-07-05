package ru.jteam.social.network.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.domain.UserSessionEntity;

/**
 * @author protsko on 11.05.18
 */
public interface UserSessionRepository {

    @Transactional
    UserSessionEntity createSession(String login);

    @Transactional
    UserSessionEntity updateSession(UserSessionEntity entity);

    @Transactional(readOnly = true)
    UserSessionEntity findByLogin(String login);

    @Transactional
    void remove(String login);

}
