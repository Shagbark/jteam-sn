package ru.jteam.social.network.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author protsko on 08.04.18
 */
@Entity
@Table(name = "application_user")
public class ApplicationUserEntity implements Serializable {

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "first_name", nullable = false, length = 40)
    private String name;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    public ApplicationUserEntity() {}

    public ApplicationUserEntity(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
