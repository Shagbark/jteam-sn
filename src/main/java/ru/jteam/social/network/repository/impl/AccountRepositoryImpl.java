package ru.jteam.social.network.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jteam.social.network.domain.AccountEntity;
import ru.jteam.social.network.repository.AccountRepository;

import java.util.List;

/**
 * @author protsko on 30.04.18
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final SessionFactory factory;

    @Autowired
    public AccountRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AccountEntity findByLogin(String login) {

        Session session = factory.getCurrentSession();
        // TODO: replace to named (native) query
        List<AccountEntity> entities = session
                .createQuery("from AccountEntity ae where ae.login = :login", AccountEntity.class)
                .setParameter("login", login)
                .getResultList();

        return entities.isEmpty() ? null : entities.get(0);
    }

    @Override
    public AccountEntity findByEmail(String email) {
        Session session = factory.getCurrentSession();

        // TODO: replace to named (native) query
        // TODO: in ApplicationUserRepository such method is exist
        List<AccountEntity> entities = session.createQuery("select ae from AccountEntity ae " +
                "join ApplicationUserEntity app_user on ae.accountId = app_user.accountId " +
                "where app_user.email = :email", AccountEntity.class)
                .setParameter("email", email)
                .getResultList();

        return entities.isEmpty() ? null : entities.get(0);
    }

    @Override
    public AccountEntity findByLoginOrEmail(String loginOrEmail) {
        Session session = factory.getCurrentSession();

        // TODO: replace to named (native) query
        List<AccountEntity> entities = session.createQuery("select ae from AccountEntity ae " +
                "join ApplicationUserEntity app_user on ae.accountId = app_user.accountId " +
                "where ae.login = :login or app_user.email = :email", AccountEntity.class)
                .setParameter("login", loginOrEmail)
                .setParameter("email", loginOrEmail)
                .getResultList();

        return entities.isEmpty() ? null : entities.get(0);
    }

}
