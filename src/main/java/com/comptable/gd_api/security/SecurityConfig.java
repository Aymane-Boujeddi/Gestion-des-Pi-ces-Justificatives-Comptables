package com.comptable.gd_api.security;

import com.comptable.gd_api.entity.Utilisateur;
import com.comptable.gd_api.enums.StatutUtilisateur;
import com.comptable.gd_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authManager,JwtUtil jwtUtil) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**","/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authManager,jwtUtil))
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .sessionFixation().none())

        ;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager,JwtUtil jwtUtil){
        return new JwtAuthenticationFilter(authenticationManager,jwtUtil);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }




    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

            return User.builder()
                    .username(utilisateur.getEmail())
                    .password(utilisateur.getPassword())
                    .authorities("ROLE_" + utilisateur.getRole().name())
                    .accountExpired(false)
                    .accountLocked(utilisateur.getStatus() != StatutUtilisateur.ACTIF)
                    .credentialsExpired(false)
                    .disabled(utilisateur.getStatus() != StatutUtilisateur.ACTIF)
                    .build();
        };
    }
}
