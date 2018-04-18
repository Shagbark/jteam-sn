package ru.jteam.social.network.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.jteam.social.network.configuration.CommonConfiguration;
import ru.jteam.social.network.repository.ApplicationUserRepository;
import ru.jteam.social.network.repository.PasswordService;
import ru.jteam.social.network.repository.impl.ApplicationUserRepositoryImpl;
import ru.jteam.social.network.repository.impl.PasswordServiceImpl;

/**
 * @author protsko on 12.04.18
 */
@Configuration
@Import({
        HibernateITConfiguration.class
})
public class SpringITConfiguration {

    @Bean
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


}
