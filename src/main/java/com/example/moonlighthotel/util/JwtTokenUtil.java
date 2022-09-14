package com.example.moonlighthotel.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

import static com.example.moonlighthotel.constant.SecurityConstant.AUTHORITIES;
import static com.example.moonlighthotel.constant.SecurityConstant.JWT_TOKEN_VALIDITY;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(getClaimsFromUser(userDetails))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(JWT_TOKEN_VALIDITY, ChronoUnit.SECONDS)))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    private Claims getClaimsFromUser(UserDetails user) {

        Claims claims = Jwts.claims();
        claims.put(AUTHORITIES, user.getAuthorities());
        return claims;
    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parserBuilder().setSigningKey("secret").build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
