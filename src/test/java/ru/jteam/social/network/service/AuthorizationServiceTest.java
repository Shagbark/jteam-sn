package ru.jteam.social.network.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.jteam.social.network.config.SpringITConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author protsko on 03.05.18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringITConfiguration.class)
public class AuthorizationServiceTest {

    @Autowired
    private AuthorizationService testAuthorizationService;

    @Autowired
    private RegistrationService testRegistrationService;

    @Test
    public void testAuthorize_withSuchLoginExists_returnTrue() {
        testRegistrationService.register(UserRegistrationMother.create("test_login", "test_password"));

        boolean result = testAuthorizationService.authorize("test_login", "test_password");
        assertTrue(result);
    }

    @Test
    public void testAuthorize_userNotExists_returnFalse() {
        boolean result = testAuthorizationService.authorize("no_login", "");
        assertFalse(result);
    }

}
