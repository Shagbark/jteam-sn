package ru.jteam.social.network.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jteam.social.network.domain.UserSessionEntity;
import ru.jteam.social.network.dto.UserSession;
import ru.jteam.social.network.repository.UserSessionRepository;
import ru.jteam.social.network.service.UserSessionService;

import java.time.LocalDate;

/**
 * @author protsko on 02.07.18
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserSessionServiceImpl(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public UserSession createSession(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login must be non null");
        }

        UserSessionEntity entity = userSessionRepository.findByLogin(login);
        if (entity != null) {
            return extendSession(entity);
        }

        entity = userSessionRepository.createSession(login);
        return new UserSession(entity.getLogin(), entity.getSession().toString());
    }

    @Override
    public UserSession findByLogin(String login) {
        UserSessionEntity entity = getEntityByLogin(login);
        return entity == null ? null : new UserSession(entity.getLogin(), entity.getSession().toString());
    }

    @Override
    public boolean isExpired(String login) {
        UserSessionEntity entity = getEntityByLogin(login);
        return entity != null && LocalDate.now().isAfter(entity.getExpiredDate());
    }

    @Override
    public void removeSession(String login) {
        if (login != null && !login.isEmpty()) {
            userSessionRepository.remove(login);
        }
    }

    private UserSessionEntity getEntityByLogin(String login) {
        if (login == null || login.isEmpty()) {
            return null;
        }
        return userSessionRepository.findByLogin(login);
    }

    private UserSession extendSession(UserSessionEntity entity) {
        entity.setExpiredDate(LocalDate.now().plusDays(1));
        entity = userSessionRepository.updateSession(entity);
        return new UserSession(entity.getLogin(), entity.getSession().toString());
    }

}
