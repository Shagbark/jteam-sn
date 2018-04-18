package ru.jteam.social.network.repository;

/**
 * @author protsko on 18.04.18
 */
public interface PasswordService {

    String encryptPassword(String password);

    boolean comparePasswords(String enteredPassword, String savedPassword);

}
