package com.daw.finalProject.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de autenticación JWT que valida el token en cada solicitud.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Servicio para gestionar el JWT
    @Autowired
    private JwtUtil jwtUtil;

    // Servicio para cargar los usuarios de base de datos
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Método que se ejecuta en cada solicitud para validar el token JWT.
     *
     * @param request     Solicitud HTTP entrante
     * @param response    Respuesta HTTP
     * @param filterChain Cadena de filtros
     * @throws ServletException En caso de errores de servlet
     * @throws IOException      En caso de errores de E/S
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String mail = null;

        // Veficar el encabezado correcto del JWT
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); // extraemos el token sin el prefijo
            mail = jwtUtil.extractUsername(token); // extraemos el mail del usuario
        }

        // si el usuario existe y no hay autenticacion previa
        if (mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // obtenemos el usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(mail);

            // validamos el token
            if (jwtUtil.validateToken(token, mail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(mail,
                        null, userDetails.getAuthorities());
                // detalles de solicitud
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // se establece la autenticacion para la solicitud
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

    }

}
