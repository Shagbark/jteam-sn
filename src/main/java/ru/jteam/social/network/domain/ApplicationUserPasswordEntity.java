package ru.jteam.social.network.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author protsko on 08.04.18
 */
@Entity
@Table(name = "application_user_password")
public class ApplicationUserPasswordEntity implements Serializable {

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    public ApplicationUserPasswordEntity() {}

    public ApplicationUserPasswordEntity(int accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
