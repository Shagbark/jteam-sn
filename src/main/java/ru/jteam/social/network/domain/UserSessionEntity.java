package ru.jteam.social.network.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * @author protsko on 11.05.18
 */
@Entity
@Table(name = "user_session")
@NamedNativeQuery(
        name = "findSessionByLogin",
        query = "SELECT * FROM user_session us WHERE us.login = :login",
        resultClass = UserSessionEntity.class
)
public class UserSessionEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID session;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(name = "expired_date", nullable = false)
    private LocalDate expiredDate;

    public UserSessionEntity() {}

    public UserSessionEntity(String login) {
        this.login = login;
        this.expiredDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
    }

    public UUID getSession() {
        return session;
    }

    public String getLogin() {
        return login;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
