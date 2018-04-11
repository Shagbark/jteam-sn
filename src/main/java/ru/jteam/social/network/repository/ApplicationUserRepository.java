package ru.jteam.social.network.repository;

import ru.jteam.social.network.domain.AccountEntity;

/**
 * @author protsko on 08.04.18
 */
public interface ApplicationUserRepository {

    AccountEntity createNewAccount(String login, String name, String lastName,
                                   String email, String password);

}
