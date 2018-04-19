package ru.jteam.social.network.validation.contraint.validator

import org.springframework.context.ApplicationContext
import ru.jteam.social.network.validation.UniqueValidator
import ru.jteam.social.network.validation.annotation.Unique
import spock.lang.Specification

/**
 * @author protsko on 19.04.18
 */
class UniqueConstraintValidatorTest extends Specification {

    private final ApplicationContext context = Mock(ApplicationContext.class)

    private UniqueConstraintValidator validator

    def setup() {
        validator = new UniqueConstraintValidator()
        validator.setApplicationContext(context)
    }

    def "initialize() - throw exception, because validator is interface"() {
        given:
            Unique unique = getInvalidAnnotation()
        when:
            validator.initialize(unique)
        then:
            thrown IllegalArgumentException.class
    }

    def "initialize() - initialize validator"() {
        given:
            Unique unique = getValidAnnotation()
        when:
            validator.initialize(unique)
        then:
            1 * context.getBean(unique.uniqueValidator())
    }

    def getInvalidAnnotation() {
        Data.class.declaredFields.find {
            Unique annotation = it.getAnnotation(Unique.class)
            annotation != null && annotation.uniqueValidator().isInterface()
        }.getAnnotation(Unique.class)
    }

    def getValidAnnotation() {
        Data.class.declaredFields.find {
            Unique annotation = it.getAnnotation(Unique.class)
            annotation != null && !annotation.uniqueValidator().isInterface()
        }.getAnnotation(Unique.class)
    }

    class Data {
        @Unique(uniqueValidator = UniqueValidator.class, message = "")
        String incorrectAnnotationData = ""
        @Unique(uniqueValidator = UniqueTestValidator.class, message = "")
        int correctData = 0
    }

    class UniqueTestValidator implements UniqueValidator {
        @Override
        boolean validate(Object value) {
            return false
        }
    }

}
