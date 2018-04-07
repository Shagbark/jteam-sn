package ru.jteam.social.network.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author protsko on 06.04.18
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailConstraintValidator.class)
public @interface Email {

    String message() default "Email should like example@example.com";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
