package ua.goit.group6.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuration for Spring mvc
 *
 * @author Boiko Ivan
 * @author Artyr
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.goit.group6.controller")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(MvcConfiguration.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.info("Handle resources");
        registry.addResourceHandler("/jpeg/**").addResourceLocations("/WEB-INF/jpeg/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.info("Handle controllers");
        registry.addViewController("/login").setViewName("login_form");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/registration").setViewName("registration_form");
        registry.addViewController("/startups/new_startup").setViewName("new_startup_form");
        registry.addViewController("/offers/new_offer").setViewName("new_offer_form");
        registry.addViewController("/admins/new_admin").setViewName("new_admin_form");

        //для работы (не заморачивайтесь), потом нужно будет удалить
        registry.addViewController("/startups/1/update").setViewName("test_startups_update_page");
        registry.addViewController("/startups/1").setViewName("test_startups_read_page");
        registry.addViewController("/startups").setViewName("test_startups_page");

    }

    //
    // "view" -> new View("/PATH/TO/view")
    //
    @Bean
    public ViewResolver viewResolver() {
        LOGGER.info("Handling view");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // mean we will work with JPS
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        // Give me view name 'users' -> JstlView(/WEB-INF/jps/ + users + .jsp)
        return resolver;
    }
}
