package ru.jteam.social.network.repository

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.query.Query
import ru.jteam.social.network.domain.AccountEntity
import ru.jteam.social.network.domain.ApplicationUserEntity
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

        factory.getCurrentSession() >> session
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
            2 * session.persist(_)
        and:
            login == result.getLogin()
            name == result.getApplicationUser().getName()
    }

    def "findByLogin() - return found entity by login"() {
        given:
            String login = "login"
        and:
            List<ApplicationUserEntity> entities = new ArrayList<>()
            entities << new ApplicationUserEntity()
        and:
            Query<ApplicationUserEntity> query = Mock(Query.class)
        when:
            ApplicationUserEntity result = repository.findByLogin(login)
        then:
            1 * session.createQuery(_ as String, ApplicationUserEntity.class) >> query
        and:
            1 * query.setParameter("login", {String passedParameter ->
                assert passedParameter == login
                return true
            }) >> query
        and:
            1 * query.getResultList() >> entities
        and:
            result != null
    }

    def "findByLogin() - return null, because no user with such login"() {
        given:
            String login = "login"
        and:
            Query<ApplicationUserEntity> query = Mock(Query.class)
        when:
            ApplicationUserEntity result = repository.findByLogin(login)
        then:
            1 * session.createQuery(_ as String, ApplicationUserEntity.class) >> query
        and:
            1 * query.setParameter("login", {String passedParameter ->
                assert passedParameter == login
                return true
            }) >> query
        and:
            1 * query.getResultList() >> new ArrayList<>()
        and:
            result == null
    }

}
