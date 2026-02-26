package com.example.hotelmanagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable());
        http.authorizeHttpRequests(httpSecurity->
                        httpSecurity.anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        DefaultSecurityFilterChain build = http.build();
        return build;
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails= User.builder()
                .username("admin")
                .password("{noop}admin@10")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}
