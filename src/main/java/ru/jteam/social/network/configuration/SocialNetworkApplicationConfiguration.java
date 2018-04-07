package ru.jteam.social.network.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author protsko on 22.03.18
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.jteam.social.network")
@Import({
        HibernateConfiguration.class,
        ThymeleafConfiguration.class
})
public class SocialNetworkApplicationConfiguration extends
        WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
    }

}
