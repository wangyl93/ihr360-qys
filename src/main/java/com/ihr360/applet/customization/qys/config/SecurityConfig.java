package com.ihr360.applet.customization.qys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                //fix the post forbidden error when using postman
                http.csrf(AbstractHttpConfigurer::disable);
                http.headers().frameOptions().sameOrigin();
                //fix end
                http.authorizeHttpRequests(
                                authorize -> authorize
                                                .anyRequest()
                                                .permitAll())
                                .oauth2Client(Customizer.withDefaults());

                return http.build();
        }
}