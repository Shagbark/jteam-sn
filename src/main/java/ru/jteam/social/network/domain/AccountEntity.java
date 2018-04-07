package ru.jteam.social.network.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author protsko on 08.04.18
 */
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_generator")
    @SequenceGenerator(name = "account_id_generator", sequenceName = "account_ids", allocationSize = 10,
            initialValue = 10000)
    private int accountId;

    @Column(name = "name", unique = true, nullable = false)
    private String login;

    @OneToOne(mappedBy = "account")
    private ApplicationUserEntity applicationUser;

    public AccountEntity() {}

    public AccountEntity(String login) {
        this.login = login;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getLogin() {
        return login;
    }
}
