package ru.jteam.social.network.validation

import ru.jteam.social.network.service.ApplicationUserService
import spock.lang.Specification
/**
 * @author protsko on 19.04.18
 */
class UniqueEmailValidatorTest extends Specification {

    private final ApplicationUserService userService = Mock(ApplicationUserService.class)

    private UniqueEmailValidator validator

    def setup() {
        this.validator = new UniqueEmailValidator(userService)
    }

    def "validate() - invoke user service method and return it's value"() {
        given:
            String email = "email@example.com"
        when:
            boolean result = validator.validate(email)
        then:
            1 * userService.isEmailExists(email) >> true
        and:
            result
    }

}
