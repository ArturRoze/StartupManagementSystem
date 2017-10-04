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
 * @author voksus
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.goit.group6.controller")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(MvcConfiguration.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.info("Handle resources");
        registry.addResourceHandler("/*.css").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/*.svg").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/*.png").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/*.jpg").addResourceLocations("/WEB-INF/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.info("Handle controllers");
        registry.addViewController("/login").setViewName("login_form");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/registration").setViewName("registration_form");
        registry.addViewController("/admins/new/admin").setViewName("admin_add_form");
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
