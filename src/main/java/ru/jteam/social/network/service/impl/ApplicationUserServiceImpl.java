package ru.jteam.social.network.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.jteam.social.network.domain.ApplicationUserEntity;
import ru.jteam.social.network.dto.ApplicationUser;
import ru.jteam.social.network.repository.ApplicationUserRepository;
import ru.jteam.social.network.service.ApplicationUserService;

/**
 * @author protsko on 19.04.18
 */
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;

    @Autowired
    public ApplicationUserServiceImpl(@Qualifier("applicationUserRepository") ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApplicationUser findByLogin(String login) {
        ApplicationUserEntity entity = find(login);
        return entity == null ? null : new ApplicationUser(login,
                entity.getName() + " " + entity.getLastName(), entity.getEmail());
    }

    @Override
    public boolean isEmailExists(String email) {
        return email != null && !email.trim().isEmpty() && userRepository.isEmailExists(email);
    }

    private ApplicationUserEntity find(String login) {
        if (login == null || login.trim().isEmpty()) {
            return null;
        }
        return userRepository.findByLogin(login);
    }

}
