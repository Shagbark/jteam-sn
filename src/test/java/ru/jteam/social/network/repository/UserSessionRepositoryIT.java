package ru.jteam.social.network.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.config.SpringITConfiguration;
import ru.jteam.social.network.domain.UserSessionEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author protsko on 15.05.18
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringITConfiguration.class)
public class UserSessionRepositoryIT {

    @Autowired
    @Qualifier("testUserSessionRepository")
    private UserSessionRepository repository;

    @Test
    public void testCreateSession_validLogin_returnCreatedSession() {
        String login = "login";

        UserSessionEntity result = repository.createSession(login);

        assertNotNull(result);
        assertNotNull(result.getSession());
        assertEquals(LocalDate.now().plus(1, ChronoUnit.DAYS), result.getExpiredDate());
    }

    @Test
    public void testFindByLogin_recordExists_returnUserSession() {
        String login = "some_login";
        repository.createSession(login);

        UserSessionEntity result = repository.findByLogin(login);
        assertNotNull(result);
        assertEquals(login, result.getLogin());
    }

    @Test
    public void testFindByLogin_noRecord_returnNull() {
        String login = "another_login";

        UserSessionEntity result = repository.findByLogin(login);
        assertNull(result);
    }

    @Test
    public void testRemove_recordExists_removeRecord() {
        String login = "login_for_remove";

        UserSessionEntity createdEntity = repository.createSession(login);

        repository.remove(login);
        UserSessionEntity recordAfterDelete = repository.findByLogin(login);
        assertNull(recordAfterDelete);
    }

}
