package com.farm_management.fms.common.config;

import com.farm_management.fms.common.exceptions.UserNotFoundException;
import com.farm_management.fms.modules.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository ;

    public UserDetailsService userDetailsService(){
        return username -> {
                Long userId = Long.parseLong(username);
                return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with this id " + userId));
        };
    }

}
