package com.greenfox.tribes1.Security;

import com.greenfox.tribes1.Security.JWT.JwtSettings;
import com.greenfox.tribes1.Security.Model.JwtToken;
import com.greenfox.tribes1.Security.Model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactory {

  private final JwtSettings settings;

  @Autowired
  public JwtTokenFactory(JwtSettings settings) {
    this.settings = settings;
  }

  public AccessJwtToken createAccessJwtToken(UserContext userContext){
    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

    LocalDateTime currentTime = LocalDateTime.now();

    String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer(settings.getTokenIssuer())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(currentTime
                .plusMinutes(settings.getTokenExpirationTime())
                .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
            .compact();

    return new AccessJwtToken(token, claims);
  }

  public JwtToken createRefreshToken(UserContext userContext){
    LocalDateTime currentTime = LocalDateTime.now();

    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

    String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer(settings.getTokenIssuer())
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(currentTime
                    .plusMinutes(settings.getRefreshTokenExpTime())
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
            .compact();

    return new AccessJwtToken(token,claims);
  }
}
