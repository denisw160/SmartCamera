package me.wirries.smartcamera.cameraservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * This is the WebSecurity configuration. Other settings are in the application.yml.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-19
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Value("${app.security.enable}")
    private boolean enabled;

    @Value("${app.security.username}")
    private String username = "user";

    @Value("${app.security.password}")
    private String password = "password";

    /**
     * Configure the authentication.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        if (enabled) {
            LOGGER.info("Activate basic authentication for this application");
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }
    }

    @Bean
    @Override
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(username)
                        .password(password)
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

}
