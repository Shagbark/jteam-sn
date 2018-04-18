package ru.jteam.social.network.dto;

/**
 * @author protsko on 17.04.18
 */
public class ApplicationUser {

    private String login;
    private String displayName;
    private String email;

    public ApplicationUser(String login, String displayName, String email) {
        this.login = login;
        this.displayName = displayName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
