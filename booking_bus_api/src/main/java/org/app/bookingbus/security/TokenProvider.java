package org.app.bookingbus.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    //    secret key for jwt
    @Value("${jwt.secret}")
    private String secretKey;

    private final static Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

//    private final UserService userService;
//
//    public TokenProvider(UserService userService) {
//        this.userService = userService;
//    }

    public String generateAccessToken(Authentication authentication) {
        String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        LocalDateTime expireTime = LocalDateTime.now().plusHours(1);
        return Jwts.builder()
                .claim("role", role)
                .subject(authentication.getName())
                .expiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSecretKey())
                .compact();
    }

    // generate refresh token
    public String generateRefreshToken(Authentication authentication) {
        String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        LocalDateTime expireTime = LocalDateTime.now().plusHours(24);
        return Jwts.builder()
                .claim("role", role)
                .subject(authentication.getName())
                .expiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSecretKey())
                .compact();
    }

    // generate active token
    public String generateActiveToken(String email) {
        LocalDateTime expireTime = LocalDateTime.now().plusHours(24);
        return Jwts.builder()
                .subject(email)
                .expiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSecretKey())
                .compact();
    }

    // get authentication for validate jwt
    public Authentication getAuthentication(String token) {
        if (token.isEmpty()) {
            return null;
        }
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        List<GrantedAuthority> roles = Arrays.stream(claims.get("role").toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        User user = new User(claims.getSubject(), "", roles);
        return new UsernamePasswordAuthenticationToken(user, null, roles);
    }

    // get secret key
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // validate token
    public boolean validateExpiredToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().before(new Date());
        } catch (JWTDecodeException e) {
            LOGGER.error("Fail: " + e.getMessage());
            return true;
        }
    }
}
