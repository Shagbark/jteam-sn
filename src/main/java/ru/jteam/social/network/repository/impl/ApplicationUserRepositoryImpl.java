package ru.jteam.social.network.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.domain.AccountEntity;
import ru.jteam.social.network.domain.ApplicationUserEntity;
import ru.jteam.social.network.domain.ApplicationUserPasswordEntity;
import ru.jteam.social.network.repository.ApplicationUserRepository;

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
    @Transactional
    public AccountEntity createNewAccount(String login, String name, String lastName,
                                          String email, String password) {
        Session session = factory.getCurrentSession();

        AccountEntity account = new AccountEntity(login);
        ApplicationUserEntity user = new ApplicationUserEntity(name, lastName, email);
        ApplicationUserPasswordEntity userPassword = new ApplicationUserPasswordEntity(account, password);

        account.setUserPasswordEntity(userPassword);
        account.setApplicationUser(user);

        session.persist(account);

        return account;
    }

}
