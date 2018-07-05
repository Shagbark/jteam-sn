package ru.jteam.social.network.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jteam.social.network.domain.UserSessionEntity;
import ru.jteam.social.network.repository.UserSessionRepository;

import java.util.List;

/**
 * @author protsko on 11.05.18
 */
@Repository("userSessionRepository")
public class UserSessionRepositoryImpl implements UserSessionRepository {

    private SessionFactory factory;

    @Autowired
    public UserSessionRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public UserSessionEntity createSession(String login) {
        Session session = factory.getCurrentSession();

        UserSessionEntity entity = new UserSessionEntity(login);
        session.persist(entity);
        session.flush();
        session.evict(entity);

        return entity;
    }

    @Override
    public UserSessionEntity updateSession(UserSessionEntity entity) {
        Session session = factory.getCurrentSession();

        session.merge(entity);

        return entity;
    }

    @Override
    public UserSessionEntity findByLogin(String login) {
        Session session = factory.getCurrentSession();

        List<UserSessionEntity> entities = session
                .createNamedQuery("findSessionByLogin", UserSessionEntity.class)
                .setParameter("login", login)
                .getResultList();

        if (entities.size() == 0) {
            return null;
        }

        UserSessionEntity entity = entities.get(0);
        session.evict(entity);
        return entity;
    }

    @Override
    public void remove(String login) {
        Session session = factory.getCurrentSession();

        session.createQuery("delete from UserSessionEntity where login = :login")
                .setParameter("login", login)
                .executeUpdate();
    }
}
