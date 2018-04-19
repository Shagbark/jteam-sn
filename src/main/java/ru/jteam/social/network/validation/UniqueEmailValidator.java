package ru.jteam.social.network.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jteam.social.network.service.ApplicationUserService;

/**
 * @author protsko on 19.04.18
 */
@Component
public class UniqueEmailValidator implements UniqueValidator {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public UniqueEmailValidator(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public boolean validate(Object value) {
        String email = (String) value;
        return applicationUserService.isEmailExists(email);
    }

}
