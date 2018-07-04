package ru.jteam.social.network.repository

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.query.Query
import ru.jteam.social.network.domain.UserSessionEntity
import ru.jteam.social.network.repository.impl.UserSessionRepositoryImpl
import spock.lang.Specification
/**
 * @author protsko on 23.05.18
 */
class UserSessionRepositoryTest extends Specification {

    private final SessionFactory factory = Mock(SessionFactory.class)
    private final Session session = Mock(Session.class)
    private final Query query = Mock(Query.class)

    private UserSessionRepository repository

    def setup() {
        this.repository = new UserSessionRepositoryImpl(factory)

        factory.getCurrentSession() >> session
    }

    def "createSession() - creates newly session by login"() {
        given:
            String login = "login"
        and:
            UserSessionEntity expectedResult = new UserSessionEntity(login)
        when:
            UserSessionEntity result = repository.createSession(login)
        then:
            1 * session.persist(_ as UserSessionEntity)
        and:
            expectedResult.getLogin() == result.getLogin()
            expectedResult.getExpiredDate() == result.getExpiredDate()
    }

    def "findByLogin() - returns value from database"() {
        given:
            String login = "login"
            List<UserSessionEntity> entities = new ArrayList<>()
            entities << new UserSessionEntity(login)
        when:
            UserSessionEntity result = repository.findByLogin(login)
        then:
            1 * session.createNamedQuery("findSessionByLogin", UserSessionEntity.class) >> query
            1 * query.setParameter("login", { String passedLogin ->
                assert passedLogin == login
                return true
            }) >> query
            1 * query.getResultList() >> entities
        and:
            result.login == login
    }

    def "remove() - execute update after creating query"() {
        given:
            String login = "login"
        when:
            repository.remove(login)
        then:
            1 * session.createQuery(_) >> query
            1 * query.setParameter("login", { String passedLogin ->
                assert passedLogin == login
                return true
            }) >> query
            1 * query.executeUpdate()
    }

}
