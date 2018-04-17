package ru.jteam.social.network.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author protsko on 12.04.18
 */
@Configuration
@EnableTransactionManagement
public class HibernateITConfiguration {

    @Bean(name = "testDataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("jteam-sn")
                .build();
    }

    @Bean(name = "testLocalSessionFactoryBean")
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setDataSource(dataSource());
        factory.setPackagesToScan("ru.jteam.social.network.domain");

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        properties.setProperty(AvailableSettings.SHOW_SQL, "true");
        properties.setProperty(AvailableSettings.FORMAT_SQL, "true");

        factory.setHibernateProperties(properties);

        return factory;
    }

    @Bean(name = "testSessionFactory")
    public SessionFactory sessionFactory() {
        return sessionFactoryBean().getObject();
    }

    @Bean(name = "testTransactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory testSessionFactory) {
        return new HibernateTransactionManager(testSessionFactory);
    }


}
