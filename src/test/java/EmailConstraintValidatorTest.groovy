import ru.jteam.social.network.validation.EmailConstraintValidator
import spock.lang.Specification

/**
 * @author protsko on 07.04.18
 */
class EmailConstraintValidatorTest extends Specification {

    private EmailConstraintValidator validator

    def setup() {
        this.validator = new EmailConstraintValidator()
    }

    def "isValid() - return true if email is valid format"(
            String email, boolean result
    ) {
        expect:
            result == validator.isValid(email, null)
        where:
            email               | result
            "ex@gmail.com"      | true
            "volume@music.org"  | true
            "ttt.to"            | false
            "@yahoo.com"        | false
            "dmitr@yandex.ru"   | true
    }

    def "isValid() - return false if email is null"() {
        expect:
            !validator.isValid(null, null)
    }

}
