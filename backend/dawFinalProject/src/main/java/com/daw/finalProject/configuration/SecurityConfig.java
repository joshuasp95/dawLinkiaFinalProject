package com.daw.finalProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.daw.finalProject.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // Servicio para obtener detalles de usuario
    @SuppressWarnings("unused")
    @Autowired
    private UserDetailsService userDetailsService;

    // Filtro para manejar JWT
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Define las reglas de seguridad para las solicitudes HTTP.
     *
     * @param http Objeto HttpSecurity para configurar las reglas.
     * @return SecurityFilterChain configurado.
     * @throws Exception en caso de error de configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF, en este caso las sesiones se guardan en JWT
                // configuracion de peticiones http entrantes
                .authorizeHttpRequests(authz -> {
                    // para autenticacion se permiten solicitudes sin autenticarse
                    authz.requestMatchers("/api/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                // manejo de sesiones con politica stateless, es decir cada peticion necesita un
                // JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Añadimos un filtro para validar el JWT antes de procesar la autenticacion
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configura el AuthenticationManager con el UserDetailsService y el
     * PasswordEncoder.
     *
     * @param http               Objeto HttpSecurity
     * @param passwordEncoder    Bean de PasswordEncoder
     * @param userDetailsService Bean de UserDetailsService
     * @return AuthenticationManager configurado
     * @throws Exception En caso de errores durante la configuración
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();

    }

    // Algoritmo usado para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
