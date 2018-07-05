package ru.jteam.social.network.dto;

/**
 * @author protsko on 30.04.18
 */
public class AuthorizationUser {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}