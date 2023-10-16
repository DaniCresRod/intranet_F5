package com.intranet_F5.Config;

import com.intranet_F5.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/schools/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/users/**")).hasRole("HR")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/request/**")).hasRole("Formador")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/request/**")).hasAnyRole("Supervisor", "HR")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/request/**")).hasAnyRole("Supervisor", "HR")
                        .requestMatchers(antMatcher(HttpMethod.GET,"/logs/**")).hasRole("HR")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManager->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}