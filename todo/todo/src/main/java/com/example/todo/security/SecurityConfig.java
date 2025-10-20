package com.example.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("api/admin/users/**").hasRole("ADMIN")
                        .requestMatchers("api/todos/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .csrf( csrf -> csrf.disable() );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
