package com.adtech.todolist.security;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ferozk
 */
public class TokenAutheraizationFilter extends BasicAuthenticationFilter {

    @Autowired
    UserRepository userRepository;

    public TokenAutheraizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenProperties.HEADER_STRING);
        if (header == null || !header.startsWith(TokenProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authendication = getUsernamePasswordAuthendication(request);
        SecurityContextHolder.getContext().setAuthentication(authendication);
        chain.doFilter(request, response);
    }


    private Authentication getUsernamePasswordAuthendication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(TokenProperties.HEADER_STRING);
        if (token != null) {
            String userName = JWT.require(Algorithm.HMAC512(TokenProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TokenProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            if (userName != null) {
                User user = userRepository.findByUserName(userName);
                MyUserDetails myUserDetails = new MyUserDetails(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, myUserDetails.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}

