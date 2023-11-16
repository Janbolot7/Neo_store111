package com.example.Neo_store111.configuration;

import com.example.Neo_store111.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfiguration {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request .requestMatchers(ADMIN_ONLY).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/to/admin/allUsers").hasRole("ADMIN")
                                .requestMatchers(USER_GENERAL).hasRole("USER")
                                .requestMatchers("/auth/login", "/auth/registration").permitAll().anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/api/vv/main", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/auth/login"))
                .build();

    }

    private final String[] PERMIT_FOR_ALL = {
            "/auth/**"
    };

    private final String[] USER_GENERAL = {
            "/api/vv/users/**",
            "/api/vv/orders/createOrder",
    };

    private final String[] ADMIN_ONLY = {
            "/api/to/admin/allUsers",
            "/api/vv/categories/**",
            "/api/vv/orders/**",
            "/api/vv/products/**"
    };
}
