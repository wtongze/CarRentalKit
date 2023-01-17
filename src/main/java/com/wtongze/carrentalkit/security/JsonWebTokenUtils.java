package com.wtongze.carrentalkit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JsonWebTokenUtils {
    private final Algorithm algorithm = Algorithm.HMAC256("secret");
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public String createToken(UserDetails userDetails) {
        var roles = new ArrayList<String>();
        for (var auth : userDetails.getAuthorities()) {
            roles.add(auth.getAuthority());
        }
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", roles)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(60 * 20))
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String token) {
        return verifier.verify(token);
    }
}
