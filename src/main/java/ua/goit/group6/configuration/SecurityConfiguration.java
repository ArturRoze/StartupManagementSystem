package ua.goit.group6.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration for Spring security.
 *
 * @author Boiko Ivan
 * @author Artyr
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ua.goit.group6.controller")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        LOGGER.info("Creating BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configuring security");

        http.authorizeRequests()
                    .antMatchers("/", "/startups", "/startups/*").permitAll()
                    .antMatchers("/registration", "/registration/**").not().authenticated()
                    .antMatchers("/news").authenticated()
                    .antMatchers("/users", "/admins", "/admins/**").hasRole("ADMIN")
                    .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")

                // for second sprint
                .antMatchers("/startups/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/offers", "/offers/**").hasAnyRole("USER", "ADMIN")

                .anyRequest().denyAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/error")
                    .defaultSuccessUrl("/news")
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                .csrf().disable();
    }
}
