package com.ayeshj.gapstar.configuration;

import com.ayeshj.gapstar.model.UserEntity;
import com.ayeshj.gapstar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Optional;

/**
 * Configuration for Spring security
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserRepository userRepository;

    /**
     * Constructor for dependency injection
     *
     * @param dataSource     Application datasource
     * @param userRepository User repository
     */
    @Autowired
    public SecurityConfig(DataSource dataSource, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    /**
     * Password encoding algorithm
     *
     * @return Encoder Bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Advanced security config
     *
     * @param auth Authentication Builder
     * @throws Exception Thrown if builder fails to initialize the authentication bean
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users where username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities where username = ?");
    }

    /**
     * Simple sample user account creator for development and testing purposes;
     */
    @PostConstruct
    public void setupRootUsers() {

        log.info("SETTING UP TEST ROOT USERS...");

        Optional<UserEntity> optionalRootUser = userRepository.findById(1);
        Optional<UserEntity> optionalRootUser1 = userRepository.findById(2);
        Optional<UserEntity> optionalRootUser2 = userRepository.findById(3);

        if (!optionalRootUser.isPresent()) {
            log.warn("TEST USER NOT FOUND FOR : admin");
            createUser(1, "admin", "admin"); //Creates an admin user
        }

        if (!optionalRootUser1.isPresent()) {
            log.warn("TEST USER NOT FOUND FOR : ayesh");
            createUser(2, "ayesh", "ayesh"); //Creates a test user
        }

        if (!optionalRootUser2.isPresent()) {
            log.warn("TEST USER NOT FOUND FOR : barak");
            createUser(3, "barak", "barak"); //Creates a test user
        }

    }

    /**
     * Creates a new user
     *
     * @param i        User id
     * @param username User name for test user
     * @param password Password for test user
     */
    private void createUser(int i, String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(i);
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder().encode(password));
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        log.info("CREATED NEW USER : {}", userEntity.getUsername());

    }
}
