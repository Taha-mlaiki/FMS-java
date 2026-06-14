package com.farm_management.fms.common.config;


import com.farm_management.fms.common.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   private final JwtAuthenticationFilter jwtAuthFilter;
   private final AuthenticationProvider authProvider ;

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       // 1. Disable CSRF (Cross-Site Request Forgery)
       http.
               csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth->
                       // Make the auth endpoints completely public (anyone can log in or register)
                       auth.requestMatchers("/api/auth/**").permitAll()
                               // Every other endpoint in your application requires a valid token
                               .anyRequest().authenticated())
               // 3. Make the session Stateless
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               // 4. Inject the Authentication Provider (The database bridge we built earlier)
               .authenticationProvider(authProvider)
               // 5. Inject your Custom JWT Filter
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
}
