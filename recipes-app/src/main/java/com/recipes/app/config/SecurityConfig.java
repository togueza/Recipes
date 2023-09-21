package com.recipes.app.config;


import com.recipes.app.entity.UserTable;
import com.recipes.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            UserTable userTable = userRepository.findByUsername(username);
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (userTable != null) {
                return new org.springframework.security.core.userdetails.User(userTable.getUsername(), encoder.encode(userTable.getPassword()),
                        true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Could not find the userTable '" + username + "'");
            }
        });
    }

}
