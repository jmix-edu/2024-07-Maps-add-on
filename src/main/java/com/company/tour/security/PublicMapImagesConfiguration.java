package com.company.tour.security;

import io.jmix.securityflowui.FlowuiSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class PublicMapImagesConfiguration extends FlowuiSecurityConfiguration {

    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(r -> r.requestMatchers("/map/**").permitAll());
        return super.securityFilterChain(http);
    }
}
