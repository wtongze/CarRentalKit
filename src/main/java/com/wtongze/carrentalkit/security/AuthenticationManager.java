package com.wtongze.carrentalkit.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JsonWebTokenUtils utils;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            DecodedJWT token = utils.decodeToken(authToken);
            String username = token.getSubject();
            List<String> rolesList = token.getClaim("roles").asList(String.class);
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    username,
                    authToken,
                    rolesList.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()))
            );
        } catch (Exception e) {
            return Mono.error(new BadCredentialsException("Bad Credentials"));
        }
    }
}
