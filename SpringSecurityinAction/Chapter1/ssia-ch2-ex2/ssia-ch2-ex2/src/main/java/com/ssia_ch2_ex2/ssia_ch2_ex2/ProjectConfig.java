package com.ssia_ch2_ex2.ssia_ch2_ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );

        var user = User.withUsername("John")
                .password("12345")
                .authorities("read")
                .build();

        var userDetailService = new InMemoryUserDetailsManager(user);

        http.userDetailsService(userDetailService);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
