package ru.jteam.social.network.service;

/**
 * @author protsko on 29.04.18
 */
public interface AuthorizationService {

    boolean authorize(String loginOrEmail, String password);

}
