package ru.jteam.social.network.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.jteam.social.network.validation.Email;
import ru.jteam.social.network.validation.FieldsMatch;

/**
 * @author protsko on 06.04.18
 */
@FieldsMatch(
        first = "password",
        second = "passwordConfirmation",
        message = "Passwords are not equals"
)
public class RegistrationDto {

    @NotBlank
    @Length(min = 6)
    private String login;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    public RegistrationDto() {}

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

}
