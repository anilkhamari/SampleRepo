package com.example.helloworld.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure HTTP security - login/logout settings.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form ->
                form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
            .csrf(csrf ->
                csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
            )
            .headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.disable())
            );

        return http.build();
    }

    /**
     * In-memory user details service with two demo users.
     * Username: user | Password: password
     * Username: admin | Password: admin123
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles("ADMIN", "USER")
            .build();
        
        UserDetails user2 = User.builder()
                .username("anil")
                .password(passwordEncoder.encode("khamari"))
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(user, admin, user2);
    }

    /**
     * Password encoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
