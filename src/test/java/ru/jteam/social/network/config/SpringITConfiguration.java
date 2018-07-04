package ru.jteam.social.network.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.jteam.social.network.configuration.CommonConfiguration;
import ru.jteam.social.network.repository.AccountRepository;
import ru.jteam.social.network.repository.ApplicationUserRepository;
import ru.jteam.social.network.repository.PasswordService;
import ru.jteam.social.network.repository.UserSessionRepository;
import ru.jteam.social.network.repository.impl.AccountRepositoryImpl;
import ru.jteam.social.network.repository.impl.ApplicationUserRepositoryImpl;
import ru.jteam.social.network.repository.impl.PasswordServiceImpl;
import ru.jteam.social.network.repository.impl.UserSessionRepositoryImpl;
import ru.jteam.social.network.service.AuthorizationService;
import ru.jteam.social.network.service.RegistrationService;
import ru.jteam.social.network.service.impl.AuthorizationServiceImpl;
import ru.jteam.social.network.service.impl.RegistrationServiceImpl;

/**
 * @author protsko on 12.04.18
 */
@Configuration
@Import({
        HibernateITConfiguration.class
})
public class SpringITConfiguration {

    @Autowired
    @Qualifier("testSessionFactory")
    private SessionFactory sessionFactory;

    @Bean(name = "testApplicationUserRepository")
    public ApplicationUserRepository userRepository(SessionFactory testSessionFactory) {
        return new ApplicationUserRepositoryImpl(testSessionFactory);
    }

    @Bean(name = "testPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(CommonConfiguration.PASSWORD_ENCODER_STRENGTH);
    }

    @Bean(name = "testPasswordService")
    public PasswordService passwordService(PasswordEncoder testPasswordEncoder) {
        return new PasswordServiceImpl(testPasswordEncoder);
    }

    @Bean(name = "testAccountRepository")
    public AccountRepository accountRepository() {
        return new AccountRepositoryImpl(sessionFactory);
    }

    @Bean(name = "testAuthorizationService")
    public AuthorizationService authorizationService(@Qualifier("testPasswordService") PasswordService passwordService,
                                                     @Qualifier("testAccountRepository") AccountRepository accountRepository) {
        return new AuthorizationServiceImpl(passwordService, accountRepository);
    }

    @Bean(name = "testRegistrationService")
    public RegistrationService registrationService(@Qualifier("testPasswordService") PasswordService passwordService,
                                                   @Qualifier("testApplicationUserRepository") ApplicationUserRepository userRepository) {
        return new RegistrationServiceImpl(userRepository, passwordService);
    }

    @Bean(name = "testUserSessionRepository")
    public UserSessionRepository userSessionRepository() {
        return new UserSessionRepositoryImpl(sessionFactory);
    }

}
