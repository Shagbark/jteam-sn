package ru.jteam.social.network.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.jteam.social.network.domain.AccountEntity;
import ru.jteam.social.network.domain.ApplicationUserEntity;
import ru.jteam.social.network.domain.ApplicationUserPasswordEntity;
import ru.jteam.social.network.repository.ApplicationUserRepository;

import java.util.List;

/**
 * @author protsko on 08.04.18
 */
@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    private final SessionFactory factory;

    @Autowired
    public ApplicationUserRepositoryImpl(@Qualifier("sessionFactory") SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AccountEntity createNewAccount(String login, String name, String lastName,
                                          String email, String password) {
        Session session = factory.getCurrentSession();

        AccountEntity account = new AccountEntity(login);
        ApplicationUserPasswordEntity userPassword = new ApplicationUserPasswordEntity(account, password);
        account.setUserPasswordEntity(userPassword);
        session.persist(account);

        ApplicationUserEntity user = new ApplicationUserEntity(name, lastName, email);
        user.setAccountId(account.getAccountId());
        account.setApplicationUser(user);

        session.persist(account);

        return account;
    }

    @Override
    public ApplicationUserEntity findByLogin(String login) {
        Session session = factory.getCurrentSession();

        // TODO: replace to named query (or native named query)
        List<ApplicationUserEntity> users = session.createQuery(
                        "select app_user from ApplicationUserEntity as app_user " +
                        "join AccountEntity as acc on acc.accountId = app_user.accountId " +
                        "where acc.login = :login", ApplicationUserEntity.class)
                .setParameter("login", login)
                .getResultList();

        return users.size() == 0 ? null : users.get(0);
    }
}
