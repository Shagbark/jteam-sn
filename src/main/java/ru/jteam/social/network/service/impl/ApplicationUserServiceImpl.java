package ru.jteam.social.network.service.impl;

import org.springframework.stereotype.Service;
import ru.jteam.social.network.dto.ApplicationUser;
import ru.jteam.social.network.service.ApplicationUserService;

/**
 * @author protsko on 19.04.18
 */
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Override
    public ApplicationUser findByLogin(String login) {
        return null;
    }
}
