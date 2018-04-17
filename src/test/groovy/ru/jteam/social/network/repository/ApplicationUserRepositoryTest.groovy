package ru.jteam.social.network.repository

import org.hibernate.Session
import org.hibernate.SessionFactory
import ru.jteam.social.network.domain.AccountEntity
import ru.jteam.social.network.repository.impl.ApplicationUserRepositoryImpl
import spock.lang.Specification

/**
 * @author protsko on 11.04.18
 */
class ApplicationUserRepositoryTest extends Specification {

    private final SessionFactory factory = Mock(SessionFactory.class)
    private final Session session = Mock(Session.class)

    private ApplicationUserRepository repository

    def setup() {
        this.repository = new ApplicationUserRepositoryImpl(factory)
    }

    def "createNewAccount() - creates account entity"() {
        given:
            String login = "login"
            String name = "name"
            String lastName = "lastName"
            String email = "email"
            String password = "password"
        when:
            AccountEntity result = repository.createNewAccount(login, name, lastName, email, password)
        then:
            1 * factory.getCurrentSession() >> session
        and:
            1 * session.persist(_)
        and:
            login == result.getLogin()
            name == result.getApplicationUser().getName()
    }

}
