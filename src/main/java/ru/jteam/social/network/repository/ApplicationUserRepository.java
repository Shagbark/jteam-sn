package ru.jteam.social.network.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.domain.AccountEntity;
import ru.jteam.social.network.domain.ApplicationUserEntity;

/**
 * @author protsko on 08.04.18
 */
public interface ApplicationUserRepository {

    @Transactional
    AccountEntity createNewAccount(String login, String name, String lastName,
                                   String email, String password);

    @Transactional(readOnly = true)
    ApplicationUserEntity findByLogin(String login);

}
