package ru.jteam.social.network.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.config.SpringITConfiguration;
import ru.jteam.social.network.domain.AccountEntity;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author protsko on 12.04.18
 */
@Commit
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringITConfiguration.class)
public class ApplicationUserRepositoryIT {

    @Autowired
    @Qualifier("itSessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationUserRepository repository;

    @Test
    public void testCreateNewAccount_validParam() {
        String name = "name";
        String login = "login";
        String email = "email@example.com";
        String lastName = "last name";
        String password = "password";

        AccountEntity result = repository.createNewAccount(login, name, lastName, email, password);
        assertNotEquals(0, result.getAccountId());
        assertNotEquals(0, result.getApplicationUser().getAccountId());

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        AccountEntity accountEntity = session.get(AccountEntity.class, result.getAccountId());
        assertNotNull(accountEntity.getApplicationUser());
        assertNotNull(accountEntity.getUserPasswordEntity());

        transaction.commit();
        session.close();
    }

}
