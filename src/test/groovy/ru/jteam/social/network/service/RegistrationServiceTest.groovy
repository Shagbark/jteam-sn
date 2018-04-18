package ru.jteam.social.network.service

import ru.jteam.social.network.domain.ApplicationUserEntity
import ru.jteam.social.network.dto.ApplicationUser
import ru.jteam.social.network.dto.UserRegistration
import ru.jteam.social.network.exception.AccountExistsException
import ru.jteam.social.network.repository.ApplicationUserRepository
import ru.jteam.social.network.repository.PasswordService
import ru.jteam.social.network.service.impl.RegistrationServiceImpl
import spock.lang.Specification
/**
 * @author protsko on 17.04.18
 */
class RegistrationServiceTest extends Specification {

    private final ApplicationUserRepository repository = Mock(ApplicationUserRepository.class)
    private final PasswordService passwordService = Mock(PasswordService.class)

    private RegistrationService service

    def setup() {
        service = new RegistrationServiceImpl(repository, passwordService)
    }

    def "register() - throw exception if dto is null"() {
        when:
            service.register(null)
        then:
            thrown NullPointerException.class
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "register() - throw exception if such user exists"() {
        given:
            UserRegistration registration = new UserRegistration(login: "login")
        when:
            service.register(registration)
        then:
            1 * repository.findByLogin({String passedLogin ->
                assert passedLogin == "login"
                return true
            }) >> new ApplicationUserEntity()
        and:
            thrown AccountExistsException.class
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "register() - invokes repository create method"(){
        given:
            UserRegistration registration = new UserRegistration(login: "login", email: "email")
        when:
            service.register(registration)
        then:
            1 * repository.findByLogin({String passedLogin ->
                assert passedLogin == "login"
                return true
            }) >> null
        and:
            1 * repository.createNewAccount("login", _, _, "email", _)

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

}
