package ru.jteam.social.network.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jteam.social.network.repository.PasswordService;

/**
 * @author protsko on 18.04.18
 */
@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {

    private final Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);

    private static final int PASSWORD_STRENGTH = 16;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encryptPassword(String password) {
        if (password == null || password.trim().length() <= 6) {
            throw new IllegalArgumentException("Password must be non null and it's " +
                    "length must be more than 6 symbols");
        }

        return passwordEncoder.encode(password);
    }

    @Override
    public boolean comparePasswords(String enteredPassword, String savedPassword) {
        return passwordEncoder.matches(enteredPassword, savedPassword);
    }

}
