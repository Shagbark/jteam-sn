package ru.jteam.social.network.validation.annotation;

import ru.jteam.social.network.validation.contraint.validator.FieldsMatchConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation is placed to type.
 * First - the first field in comparing, second - the second field.
 *
 * NOTE! The types of fields must be identical and in this class must be
 * implemented <code>equals()</code> and <code>hashCode()</code> methods.
 *
 * @author protsko on 07.04.18
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsMatchConstraintValidator.class)
public @interface FieldsMatch {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

}
