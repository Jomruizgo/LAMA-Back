package com.hackaton.msvc_user.configuration.security;

import com.hackaton.msvc_user.configuration.security.jwt.JwtValidatorFilter;
import com.hackaton.msvc_user.domain.spi.ITokenServicePort;
import com.hackaton.msvc_user.domain.util.Constants;
import com.hackaton.msvc_user.domain.util.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final ITokenServicePort tokenPort;


    public WebSecurityConfig(ITokenServicePort tokenPort) {
        this.tokenPort = tokenPort;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, Constants.API_AUTH_PATH + Constants.LOGIN_SEMI_PATH).permitAll();
                    http.requestMatchers(HttpMethod.POST, Constants.API_USER_PATH + Constants.CLIENT_SEMI_PATH).hasRole(UserRole.ADMIN.getRoleName());
                    http.requestMatchers(HttpMethod.DELETE, Constants.API_USER_PATH + Constants.CLIENT_SEMI_PATH + "/{userId}").hasRole(UserRole.ADMIN.getRoleName());
                    http.requestMatchers(HttpMethod.GET, Constants.API_USER_PATH + Constants.CLIENT_SEMI_PATH + "/{userId}").authenticated();
                    http.requestMatchers(HttpMethod.GET, Constants.API_USER_PATH + Constants.CLIENT_SEMI_PATH).hasRole(UserRole.ADMIN.getRoleName());
                    http.requestMatchers(HttpMethod.PUT, Constants.API_USER_PATH + Constants.CLIENT_SEMI_PATH + "/{userId}").hasRole(UserRole.ADMIN.getRoleName());
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtValidatorFilter(tokenPort), BasicAuthenticationFilter.class);


        return httpSecurity.build();
    }


}