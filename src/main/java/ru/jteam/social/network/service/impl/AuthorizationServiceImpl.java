package ru.jteam.social.network.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jteam.social.network.domain.AccountEntity;
import ru.jteam.social.network.domain.ApplicationUserPasswordEntity;
import ru.jteam.social.network.repository.AccountRepository;
import ru.jteam.social.network.repository.PasswordService;
import ru.jteam.social.network.service.AuthorizationService;

/**
 * @author protsko on 29.04.18
 */
@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

    private final PasswordService passwordService;
    private final AccountRepository accountRepository;

    @Autowired
    public AuthorizationServiceImpl(PasswordService passwordService, AccountRepository accountRepository) {
        this.passwordService = passwordService;
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean authorize(String loginOrEmail, String password) {
        AccountEntity account = accountRepository.findByLoginOrEmail(loginOrEmail);
        if (account == null) {
            return false;
        }

        ApplicationUserPasswordEntity passwordEntity = account.getUserPasswordEntity();
        return passwordService.comparePasswords(password, passwordEntity.getPassword());
    }

}
