package ru.jteam.social.network.validation.annotation;

import ru.jteam.social.network.validation.contraint.validator.UniqueConstraintValidator;
import ru.jteam.social.network.validation.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author protsko on 19.04.18
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConstraintValidator.class)
public @interface Unique {

    String message();

    /* We couldn't create field, which starts with "valid".
     * Will be thrown javax.validation.ConstraintDefinitionException
     * So this field was renamed to "uniqueValidator" */
    Class<? extends UniqueValidator> uniqueValidator();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
