package ru.jteam.social.network.dto;

/**
 * @author protsko on 30.04.18
 */
public class AuthorizationUser {

    private String loginOrEmail;
    private String password;

    public String getLoginOrEmail() {
        return loginOrEmail;
    }

    public void setLoginOrEmail(String loginOrEmail) {
        this.loginOrEmail = loginOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}