package com.haisia.shop.auth.service.domain.config;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTokenProvider {

  private final Key secretKey;
  private final long expirationMs;

  public JwtTokenProvider(Key secretKey, long expirationMs) {
    this.secretKey = secretKey;
    this.expirationMs = expirationMs;
  }

  public String generate(UserAuthId userAuthId, String email) {
    Map<String, Object> claims = new HashMap<>();
    String jti = UUID.randomUUID().toString();
    claims.put("jti", jti);
    claims.put("id", userAuthId.getValue().toString());
    claims.put("email", email);

    return Jwts.builder()
      .claims(claims)
      .subject(userAuthId.toString())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + expirationMs))
      .signWith(secretKey)
      .compact()
      ;
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
      .verifyWith((javax.crypto.SecretKey) secretKey)
      .build()
      .parseSignedClaims(token)
      .getPayload()
      ;
  }

  public boolean validate(String token) {
    try {
      return Jwts.parser()
        .verifyWith((javax.crypto.SecretKey) secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration()
        .after(new Date(System.currentTimeMillis()));
    } catch (RuntimeException ex) {
      return false;
    }
  }

  public UserAuthId getUserAuthId(String token) {
    return new UserAuthId(UUID.fromString((String) getClaims(token).get("id")));
  }

  public String getEmail(String token) {
    return (String) getClaims(token).get("email");
  }
}