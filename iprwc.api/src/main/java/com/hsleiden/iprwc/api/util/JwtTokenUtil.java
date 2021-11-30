package com.hsleiden.iprwc.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hsleiden.iprwc.api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

import static com.hsleiden.iprwc.api.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.hsleiden.iprwc.api.model.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {

    public String generateToken(User user) {return doGenerateToken(user);}

    private String doGenerateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .withIssuer("localhost:8080")
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
