package ru.jteam.social.network.validation

import ru.jteam.social.network.service.ApplicationUserService
import spock.lang.Specification
/**
 * @author protsko on 19.04.18
 */
class UniqueLoginValidatorTest extends Specification {

    private final ApplicationUserService applicationUserService = Mock(ApplicationUserService.class)

    private UniqueLoginValidator validator

    def setup() {
        this.validator = new UniqueLoginValidator(applicationUserService)
    }

    def "validate() - get user and check is exist"() {
        given:
            String login = "login"
        when:
            boolean result = validator.validate(login)
        then:
            1 * applicationUserService.findByLogin(login) >> null
        and:
            result
    }

}
