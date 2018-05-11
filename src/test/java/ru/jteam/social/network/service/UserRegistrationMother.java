package ru.jteam.social.network.service;

import ru.jteam.social.network.dto.UserRegistration;

/**
 * @author protsko on 11.05.18
 */
public final class UserRegistrationMother {

    private UserRegistrationMother() {}

    public static UserRegistration create() {
        return create("login", "email@example.com", "password");
    }

    public static UserRegistration create(String login, String password) {
        return create(login, "email@example.com", password);
    }

    public static UserRegistration create(String login, String email, String password) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setLogin(login);
        userRegistration.setEmail(email);
        userRegistration.setName("name");
        userRegistration.setLastName("last name");
        userRegistration.setPassword(password);

        return userRegistration;
    }

}
