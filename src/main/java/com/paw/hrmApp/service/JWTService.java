package com.paw.hrmApp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paw.hrmApp.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTService {
    private String secret = "hass";
    private int expireTime = 360000;

    public String generateToken(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JWT.create()
                .withClaim("ROLES", roles)
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT verifyToken(String token) {
        DecodedJWT verifiedToken = null;
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            try {
                verifiedToken = JWT.require(Algorithm.HMAC256(secret))
                        .build()
                        .verify(token.replace("Bearer ", ""));
            } catch (TokenExpiredException e) {
                throw e;
            } catch (JWTVerificationException e) {
                return null;
            }
        }

        return verifiedToken;
    }

    public UserDetailsImpl getUserFromToken(DecodedJWT decodedJWT) {
        return new UserDetailsImpl(
                decodedJWT.getSubject(),
                null,
                decodedJWT.getClaim("ROLES").asList(String.class).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}
