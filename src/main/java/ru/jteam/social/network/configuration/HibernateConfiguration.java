package ru.jteam.social.network.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * @author protsko on 08.04.18
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class HibernateConfiguration {

    private final Environment env;

    @Autowired
    public HibernateConfiguration(Environment env) {
        this.env = env;
    }

    @Bean(name = {
            "factory",
            "sessionFactory"
    })
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setDataSource(dataSource);
        factory.setPackagesToScan("ru.jteam.social.network.domain");
        factory.setHibernateProperties(defineHibernateProperties());

        return factory;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getRequiredProperty("database.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("database.url"));
        dataSource.setUser(env.getRequiredProperty("database.user"));
        dataSource.setPassword(env.getRequiredProperty("database.password"));

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Properties defineHibernateProperties() {
        Properties properties = new Properties();

        properties.put(DIALECT, env.getRequiredProperty("hbm.dialect"));
//        properties.put(CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hbm.current.session.context.class"));
        properties.put(SHOW_SQL, env.getRequiredProperty("hbm.show.sql"));
        properties.put(HBM2DDL_AUTO, env.getRequiredProperty("hbm.ddl2.auto"));

        properties.put(C3P0_MIN_SIZE, env.getRequiredProperty("hbm.c3p0.min.size"));
        properties.put(C3P0_MAX_SIZE, env.getRequiredProperty("hbm.c3p0.max.size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, env.getRequiredProperty("hbm.c3p0.acquire.increment"));
        properties.put(C3P0_MAX_STATEMENTS, env.getRequiredProperty("hbm.c3p0.max.statements"));

        properties.put(C3P0_IDLE_TEST_PERIOD, env.getRequiredProperty("hbm.c3p0.idle.test.period"));

        properties.put("hibernate.c3p0.acquireRetryAttempts", env.getRequiredProperty("hbm.c3p0.acquire.retry.attempts"));
        properties.put("hibernate.c3p0.acquireRetryDelay", env.getRequiredProperty("hbm.c3p0.acquire.retry.delay"));
        properties.put(C3P0_TIMEOUT, env.getRequiredProperty("hbm.c3p0.timeout"));

        return properties;
    }

}
