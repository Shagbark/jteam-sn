package ru.jteam.social.network.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.jteam.social.network.config.SpringITConfiguration;

import static org.junit.Assert.assertNotEquals;

/**
 * @author protsko on 18.04.18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringITConfiguration.class)
public class PasswordServiceIT {

    @Autowired
    @Qualifier(value = "testPasswordService")
    private PasswordService passwordService;

    @Test
    public void testEncryptPassword() {
        String password = "password";
        String encryptedPassword = passwordService.encryptPassword(password);

        assertNotEquals(password, encryptedPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncryptPassword_emptyString_throwException() {
        passwordService.encryptPassword(null);
    }

}
