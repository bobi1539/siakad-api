package com.siakad.filter;

import com.siakad.constant.Constant;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.JwtComponent;
import com.siakad.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    public static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String email = getEmail(request);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            setAuthentication(request, email);
        }

        filterChain.doFilter(request, response);
    }

    private String getEmail(HttpServletRequest request) {
        String authHeader = request.getHeader(Constant.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(BEARER)) {
            String token = authHeader.substring(7);
            JwtComponent jwtComponent = jwtService.extractToken(token);
            setAttributeHeader(request, jwtComponent);
            return jwtComponent.getUserEmail();
        }

        return null;
    }

    private void setAttributeHeader(HttpServletRequest request, JwtComponent jwtComponent) {
        Header header = Header.builder()
                .userId(Long.parseLong(jwtComponent.getUserId()))
                .userFullName(jwtComponent.getUserFullName())
                .build();
        request.setAttribute(Constant.HEADER, header);
    }

    private void setAuthentication(HttpServletRequest request, String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
