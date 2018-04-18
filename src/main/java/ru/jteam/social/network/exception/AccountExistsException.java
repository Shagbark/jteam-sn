package ru.jteam.social.network.exception;

/**
 * @author protsko on 17.04.18
 */
public class AccountExistsException extends RuntimeException {

    public AccountExistsException(String login) {
        super("Account with login \"" + login + "\" exists");
    }

}
