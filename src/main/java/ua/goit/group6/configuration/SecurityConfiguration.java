package ua.goit.group6.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration for Spring security.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ua.goit.group6.controller")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// Roles ADMIN and USER are in memory.
//  @Autowired
//  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//    auth.inMemoryAuthentication().withUser("artur").password("artur").roles("ADMIN");
//  }

    // We will use BC password encoder and http basic configuration.
    // Configure this all by configure method.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 1. USER can check all users in the system.
                // 2. ADMIN can create, update or delete users.
                //TODO rewrite for our servlets
                .antMatchers("/", "/login", "/registration", "/register").permitAll()
                .antMatchers("/???????", "/???????????").hasAnyRole("USER", "ADMIN")
                .antMatchers("/?????????**", "?????????/**").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/??????????")
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                // logout for all
                .logout().permitAll()
                // logout URL
                .logoutUrl("/logout")
                // URL at a successful logout
                .logoutSuccessUrl("/login?logout")
                // make invisible current session
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }
}
