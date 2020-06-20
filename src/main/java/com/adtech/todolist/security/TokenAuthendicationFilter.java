package com.adtech.todolist.security;

import com.adtech.todolist.model.request.UserCredentials;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ferozk
 */
public class TokenAuthendicationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    public TokenAuthendicationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserCredentials userCredentials = null;
        try {
            userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userCredentials.getUser(),
                userCredentials.getPass(),
                new ArrayList<>());
        Authentication authentication = authenticationManager.authenticate(token);

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetails principal = (MyUserDetails) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TokenProperties.TOKEN_EXPIRY))
                .sign(Algorithm.HMAC512(TokenProperties.SECRET.getBytes()));
        response.addHeader(TokenProperties.HEADER_STRING, TokenProperties.TOKEN_PREFIX + token);
    }
}
