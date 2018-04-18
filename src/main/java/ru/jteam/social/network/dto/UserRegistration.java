package ru.jteam.social.network.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.jteam.social.network.validation.annotation.Email;
import ru.jteam.social.network.validation.annotation.FieldsMatch;
import ru.jteam.social.network.validation.annotation.Unique;
import ru.jteam.social.network.validation.UniqueLoginValidator;

/**
 * @author protsko on 06.04.18
 */
@FieldsMatch(
        first = "password",
        second = "passwordConfirmation",
        message = "Passwords are not equals"
)
public class UserRegistration {

    @NotBlank
    @Length(min = 6)
    @Unique(uniqueValidator = UniqueLoginValidator.class,
            message = "Such login exists.")
    private String login;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    public UserRegistration() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
