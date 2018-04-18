package ru.jteam.social.network.service

import org.springframework.security.crypto.password.PasswordEncoder
import ru.jteam.social.network.repository.PasswordService
import ru.jteam.social.network.repository.impl.PasswordServiceImpl
import spock.lang.Specification

/**
 * @author protsko on 18.04.18
 */
class PasswordServiceTest extends Specification {

    private final PasswordEncoder passwordEncoder = Mock(PasswordEncoder.class)

    private PasswordService service

    def setup() {
        this.service = new PasswordServiceImpl(passwordEncoder)
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "encryptPassword() - encrypt entered password"() {
        given:
            String password = "password"
            String encryptedPassword = "encrypted password"
        when:
            String result = service.encryptPassword(password)
        then:
            1 * passwordEncoder.encode({String passedPassword ->
                assert passedPassword == password
                return true
            }) >> encryptedPassword
        and:
            encryptedPassword == result
    }

    def "encryptPassword() - throw IllegalArgumentException if password is not valid"() {
        when:
            service.encryptPassword(null)
        then:
            thrown IllegalArgumentException.class
    }

    def "comparePasswords() - should compare two passwords"() {
        given:
            String enteredPassword = "password"
            String savedPassword = "saved password"
        when:
            service.comparePasswords(enteredPassword, savedPassword)
        then:
            1 * passwordEncoder.matches(enteredPassword, savedPassword) >> true
    }

}
