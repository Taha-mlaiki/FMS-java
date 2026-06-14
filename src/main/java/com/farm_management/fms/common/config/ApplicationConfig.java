package com.farm_management.fms.common.config;

import com.farm_management.fms.modules.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository ;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
                Long userId = Long.parseLong(username);
                return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with this id " + userId));
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // 1. Pass the service directly into the constructor (fulfills the 1 expected argument)
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());

        // 2. Only set the password encoder (the setter for the user service is gone)
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
