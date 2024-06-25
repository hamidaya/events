package org.music.events.config;

import org.music.events.filter.JwtRequestFilter;
import org.music.events.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }


    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth

                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
              

                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()

                .requestMatchers(HttpMethod.GET,"/events").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/events").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"/events/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT, "/events").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE, "/events").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/events/**").hasAnyRole("ADMIN","USER")

                .requestMatchers(HttpMethod.GET,"/festivals").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/festivals").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"/festivals/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT, "/festivals").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE, "/festivals").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/festivals/**").hasAnyRole("ADMIN","USER")

                  .requestMatchers(HttpMethod.GET,"/partys").hasAnyRole("ADMIN", "USER")
                  .requestMatchers(HttpMethod.POST, "/partys").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.POST,"/partys/**").hasRole("ADMIN")
                  .requestMatchers(HttpMethod.PUT, "/partys").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.DELETE, "/partys").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.DELETE,"/partys/**").hasAnyRole("ADMIN","USER")

                  .requestMatchers(HttpMethod.PUT, "/profiles").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.PUT, "/profiles/**").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.GET, "/profiles").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.GET, "/profiles/**").hasAnyRole("ADMIN","USER")

                  .requestMatchers(HttpMethod.GET,"/tickets/**").hasAnyRole("ADMIN", "USER")
                  .requestMatchers(HttpMethod.POST, "/tickets").hasAnyRole("ADMIN","USER")
                  .requestMatchers(HttpMethod.POST, "/tickets/**").hasAnyRole("ADMIN","USER")
                  .anyRequest().denyAll()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}