package ru.jteam.social.network.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author protsko on 07.04.18
 */
public class FieldsMatchConstraintValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String first;
    private String second;

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);

        Object firstFieldValue = beanWrapper.getPropertyValue(first);
        Object secondFieldValue = beanWrapper.getPropertyValue(second);

        return Objects.equals(firstFieldValue, secondFieldValue);
    }

}
