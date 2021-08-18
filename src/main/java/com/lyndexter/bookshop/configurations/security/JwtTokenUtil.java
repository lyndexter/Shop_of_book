package com.lyndexter.bookshop.configurations.security;

import com.lyndexter.bookshop.models.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
  private final String jwtIssuer = "example.io";

  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(format("%s,%s", user.getId(), user.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserId(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

    return claims.getSubject().split(",")[0];
  }

  public String getUsername(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

    return claims.getSubject().split(",")[1];
  }

  public Date getExpirationDate(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

    return claims.getExpiration();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      System.out.format("Invalid JWT signature - %s", ex.getMessage());
    } catch (MalformedJwtException ex) {
      System.out.format("Invalid JWT token - %s", ex.getMessage());
    } catch (ExpiredJwtException ex) {
      System.out.format("Expired JWT token - %s", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
      System.out.format("Unsupported JWT token - %s", ex.getMessage());
    } catch (IllegalArgumentException ex) {
      System.out.format("JWT claims string is empty - %s", ex.getMessage());
    }
    return false;
  }
}
