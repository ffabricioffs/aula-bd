
package com.example.aulabd.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(
            "SELECT nome, password, true FROM aluno WHERE nome = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
            "SELECT p.alunoid, p.cargo FROM perfil p " +
            "JOIN aluno a ON p.alunoid = a.id WHERE a.nome = ?"
        );
    
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
          throws Exception {

        return http
          .csrf(csrf -> csrf.disable())
 
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/").permitAll()
              .requestMatchers("/aluno").permitAll()
              .requestMatchers("/matricula").hasRole("admin")
              .anyRequest().authenticated()
          )
 
          .httpBasic(Customizer.withDefaults())
 
          .build();
    }
}




