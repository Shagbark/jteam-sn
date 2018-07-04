package ru.jteam.social.network.service

import ru.jteam.social.network.domain.UserSessionEntity
import ru.jteam.social.network.dto.UserSession
import ru.jteam.social.network.repository.UserSessionRepository
import ru.jteam.social.network.service.impl.UserSessionServiceImpl
import spock.lang.Specification

import java.time.LocalDate

/**
 * @author protsko on 02.07.18
 */
class UserSessionServiceTest extends Specification {

    private final UserSessionRepository repository = Mock(UserSessionRepository.class)

    private UserSessionService service

    def setup() {
        this.service = new UserSessionServiceImpl(repository)
    }

    //
    // Tests for method create(String login)
    //

    def "create() - invoke repository create method for valid login"() {
        given:
            String login = "login"
            UserSessionEntity entity = new UserSessionEntity(login: login, session: UUID.randomUUID())
        when:
            UserSession result = service.createSession(login)
        then:
            1 * repository.createSession(login) >> entity
        and:
            result.login == entity.login
    }

    def "create() - throw IllegalArgumentException for empty login string"() {
        when:
            service.createSession("")
        then:
            thrown IllegalArgumentException.class
    }

    def "create() - throw IllegalArgumentException for null login"() {
        when:
            service.createSession(null)
        then:
           thrown IllegalArgumentException.class
    }

    //
    // Tests for findByLogin(String login)
    //

    def "findByLogin() - invoke repository method for valid login, and such session exists"() {
        given:
            String login = "login"
            UserSessionEntity entity = new UserSessionEntity(login: login, session: UUID.randomUUID())
        when:
            UserSession result = service.findByLogin(login)
        then:
            1 * repository.findByLogin(login) >> entity
        and:
            result.login == entity.login
    }

    def "findByLogin() - invoke repository method for valid login, but return null, because session doesn't exist"() {
        given:
            String login = "login"
        when:
            UserSession result = service.findByLogin(login)
        then:
            1 * repository.findByLogin(login) >> null
        and:
            result == null
    }

    def "findByLogin() - return null for empty login and not invoke repository method"() {
        when:
            UserSession result = service.findByLogin("")
        then:
            0 * repository.findByLogin(_)
        and:
            result == null
    }

    def "findByLogin() - return null for null login and not invoke repository method"() {
        when:
            UserSession result = service.findByLogin(null)
        then:
            0 * repository.findByLogin(_)
        and:
            result == null
    }

    //
    // Tests for method isExpired(String login)
    //

    def "isExpired() - return true when data expired"() {
        given:
            String login = "login"
            LocalDate date = LocalDate.now().minusDays(2)
            UserSessionEntity entity = new UserSessionEntity(login: "login", session: UUID.randomUUID(), expiredDate: date)
        when:
            boolean result = service.isExpired(login)
        then:
            1 * repository.findByLogin(login) >> entity
        and:
            result
    }

    def "isExpired() - return false when date is not expired"() {
        given:
            String login = "login"
            LocalDate date = LocalDate.now().plusDays(1)
            UserSessionEntity entity = new UserSessionEntity(login: "login", session: UUID.randomUUID(), expiredDate: date)
        when:
            boolean result = service.isExpired(login)
        then:
            1 * repository.findByLogin(login) >> entity
        and:
            !result
    }

    def "isExpired() - return false for not existing entity"() {
        given:
            String login = "login"
        when:
            boolean result = service.isExpired(login)
        then:
            1 * repository.findByLogin(login) >> null
        and:
            !result
    }

    //
    // Tests for method removeSession(String login)
    //

    def "removeSession() - invoke repository method, because login is valid"() {
        given:
            String login = "login"
        when:
            service.removeSession(login)
        then:
            1 * repository.remove(login)
    }

    def "removeSession() - nothing do, because login is empty"() {
        when:
            service.removeSession("")
        then:
            0 * repository.remove(_)
    }

    def "removeSession() - nothing do, because login is null"() {
        when:
            service.removeSession(null)
        then:
            0 * repository.remove(_)
    }

}
