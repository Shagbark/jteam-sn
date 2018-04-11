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
    @OneToOne
    @JoinColumn(name = "account_id")
    AccountEntity account;

    @Column(name = "password", nullable = false)
    private String password;

    public ApplicationUserPasswordEntity() {}

    public ApplicationUserPasswordEntity(AccountEntity account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
