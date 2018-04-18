package ru.jteam.social.network.validation;

import org.springframework.stereotype.Component;

/**
 * @author protsko on 19.04.18
 */
@Component
public class UniqueEmailValidator implements UniqueValidator {
    @Override
    public boolean validate(Object value) {
        return false;
    }
}
