package com.haisia.shop.auth.service.domain.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {

  private final Key refreshTokenKey;
  private final long refreshTokenExpirationMs;
  private final Key accessTokenKey;
  private final long accessTokenExpirationMs;

  public JwtConfig(
    @Value("${jwt.refresh-token.key}") String refreshTokenKey,
    @Value("${jwt.refresh-token.expiration-ms}") long refreshTokenExpirationMs,
    @Value("${jwt.access-token.key}") String accessTokenKey,
    @Value("${jwt.access-token.expiration-ms}") long accessTokenExpirationMs
  ) {
    this.refreshTokenKey = Keys.hmacShaKeyFor(refreshTokenKey.getBytes());
    this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    this.accessTokenKey = Keys.hmacShaKeyFor(accessTokenKey.getBytes());
    this.accessTokenExpirationMs = accessTokenExpirationMs;
  }

  @Bean
  public JwtTokenProvider refreshTokenProvider() {
    return new JwtTokenProvider(refreshTokenKey, refreshTokenExpirationMs);
  }

  @Bean
  public JwtTokenProvider accessTokenProvider() {
    return new JwtTokenProvider(accessTokenKey, accessTokenExpirationMs);
  }
}
