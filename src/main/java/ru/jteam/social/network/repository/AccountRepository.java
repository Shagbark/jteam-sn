package ru.jteam.social.network.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.jteam.social.network.domain.AccountEntity;

/**
 * @author protsko on 29.04.18
 */
public interface AccountRepository {

    @Transactional(readOnly = true)
    AccountEntity findByLogin(String login);

    @Transactional(readOnly = true)
    AccountEntity findByEmail(String email);

    @Transactional(readOnly = true)
    AccountEntity findByLoginOrEmail(String loginOrEmail);

}
