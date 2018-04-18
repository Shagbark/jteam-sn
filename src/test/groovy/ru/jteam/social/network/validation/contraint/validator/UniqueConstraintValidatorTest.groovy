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
    private final Data object = new Data()

    private UniqueConstraintValidator validator

    def setup() {
        validator = new UniqueConstraintValidator()
        validator.setApplicationContext(context)
    }

    def "initialize() - throw exception, because validator is interface"() {
        given:
            Unique unique = getAnnotation(true)
//            println unique.uniqueValidator()
        expect:
            validator.initialize(null)
    }

    def getAnnotation(boolean validAnnotation) {
        object.class.fields.each {
            println it.type
            if (it.getAnnotation(Unique.class) == null) {
                println it
            }
        }
        return null
//        data.class.fields.find({ field ->
//            println field.getName()
////            field.getAnnotation(Unique.class).uniqueValidator() == UniqueValidator.class
//        })
    }

    class Data {
        @Unique(uniqueValidator = UniqueValidator.class, message = "")
        String incorrectAnnotationData = ""
        @Unique(uniqueValidator = UniqueTestValidator.class, message = "")
        int correctData = 0
    }

    class UniqueTestValidator implements UniqueValidator {
        @Override
        public boolean validate(Object value) {
            return false
        }
    }

}
