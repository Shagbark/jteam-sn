package ru.jteam.social.network.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author protsko on 08.04.18
 */
@Entity
@Table(name = "application_user")
public class ApplicationUserEntity implements Serializable {

    @Id
    @Column(name = "account_id")
//    @GeneratedValue(generator = "application_user_id_foreign_gen")
//    @GenericGenerator(strategy = "foreign", name = "application_user_id_foreign_gen",
//            parameters = @Parameter(name = "property", value = "accountentity"))
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
}
