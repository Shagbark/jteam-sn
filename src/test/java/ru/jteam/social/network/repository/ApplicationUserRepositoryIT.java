package ru.jteam.social.network.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringITConfiguration.class)
public class ApplicationUserRepositoryIT {

    @Autowired
    private ApplicationUserRepository repository;

    @Test
    @Commit
    public void testCreateNewAccount_validParam() {
        String name = "name";
        String login = "login";
        String email = "email@example.com";
        String lastName = "last name";
        String password = "password";

        AccountEntity result = repository.createNewAccount(login, name, lastName, email, password);
        assertNotNull(result);
        assertNotEquals(0, result.getAccountId());
    }

}
