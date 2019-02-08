package com.greenfox.tribes1.Security.Model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {
  private Jws<Claims> claims;

  private RefreshToken(Jws<Claims> claims) {
    this.claims = claims;
  }

  public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
    Jws<Claims> claims = token.parseClaims(signingKey);
    return Optional.of(new RefreshToken(claims));
  }

  @Override
  public String getToken() {
    return null;
  }

  public Jws<Claims> getClaims() {
    return claims;
  }

  public String getJti() {
    return claims.getBody().getId();
  }

  public String getSubject() {
    return claims.getBody().getSubject();
  }
}