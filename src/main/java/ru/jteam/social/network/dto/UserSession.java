package ru.jteam.social.network.dto;

/**
 * @author protsko on 25.05.18
 */
public class UserSession {

    private String login;
    private String sessionId;

    public UserSession(String login, String sessionId) {
        this.login = login;
        this.sessionId = sessionId;
    }

    public String getLogin() {
        return login;
    }

    public String getSessionId() {
        return sessionId;
    }
}
