package ru.jteam.social.network.service

import ru.jteam.social.network.domain.ApplicationUserEntity
import ru.jteam.social.network.dto.ApplicationUser
import ru.jteam.social.network.repository.ApplicationUserRepository
import ru.jteam.social.network.service.impl.ApplicationUserServiceImpl
import spock.lang.Specification

/**
 * @author protsko on 19.04.18
 */
class ApplicationUserServiceTest extends Specification {

    private final ApplicationUserRepository repository = Mock(ApplicationUserRepository.class)

    private ApplicationUserService service

    def setup() {
        this.service = new ApplicationUserServiceImpl(repository)
    }

    def "findByLogin() - not invoke repository method when login is null"() {
        when:
            ApplicationUser result = service.findByLogin(null)
        then:
            0 * repository.findByLogin(_ as String)
        and:
            result == null
    }

    def "findByLogin() - not invoke repository method when login is empty"() {
        when:
            ApplicationUser result = service.findByLogin("")
        then:
            0 * repository.findByLogin(_ as String)
        and:
            result == null
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "findByLogin() - return null if no entity in database"() {
        given:
            String login = "some login"
        when:
            ApplicationUser result = service.findByLogin(login)
        then:
            1 * repository.findByLogin({String passedLogin ->
                assert passedLogin == login
                return true
            }) >> null
        and:
            result == null
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "findByLogin() - validate string and get entity from database"() {
        given:
            String login = "some login"
        when:
            ApplicationUser result = service.findByLogin(login)
        then:
            1 * repository.findByLogin({String passedLogin ->
                assert passedLogin == login
                return true
            }) >> new ApplicationUserEntity()
        and:
            result != null
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "isEmailExists() - if email is null, empty or consist only from whitespaces, return false"(
            boolean result, String email
    ) {
        expect:
            result == service.isEmailExists(email)
        where:
            result | email
            false  | null
            false  | ""
            false  | "    "
    }

    def "isEmailExists() - return true, if email exists"() {
        given:
            String email = "email@example.com"
        when:
            boolean result = service.isEmailExists(email)
        then:
            1 * repository.isEmailExists({ String passedEmail ->
                assert passedEmail == email
                return true
            }) >> true
        and:
            result
    }

}
