package ru.jteam.social.network.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.jteam.social.network.repository.ApplicationUserRepository;
import ru.jteam.social.network.repository.impl.ApplicationUserRepositoryImpl;

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

}
