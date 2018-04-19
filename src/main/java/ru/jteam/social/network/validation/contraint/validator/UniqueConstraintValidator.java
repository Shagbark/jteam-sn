package ru.jteam.social.network.validation.contraint.validator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.jteam.social.network.validation.UniqueValidator;
import ru.jteam.social.network.validation.annotation.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author protsko on 19.04.18
 */
public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private UniqueValidator validator;

    @Override
    public void initialize(Unique constraintAnnotation) {
        Class<? extends UniqueValidator> validator = constraintAnnotation.uniqueValidator();
        if (validator.isInterface()) {
            throw new IllegalArgumentException("UniqueValidator must be a class, not interface");
        }
        this.validator = applicationContext.getBean(validator);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return validator.validate(value);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
