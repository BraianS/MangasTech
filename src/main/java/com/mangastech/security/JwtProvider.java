package com.mangastech.security;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${mangastech.app.jwtSecret}")
    private String jwtSecret;

    @Value("${mangastech.app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expireDate = new Date(now.getTime()+jwtExpirationInMs);

        return Jwts.builder()
                        .setSubject((usuarioPrincipal.getUsername()))
                        .setIssuedAt(new Date())
                        .setExpiration(expireDate)
                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                        .compact();
    }

    public String getUsuarioNomeFromJwtToken(String token) {
        return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
    }

    public boolean validateJwtToken(String authtoken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authtoken);
            return true;
        } catch (SignatureException e) {
            logger.error("JWT assinatura invalida -> Mensagem: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("JWT token inválido -> Mensagem: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expirado -> Mensagem: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token não é suportado -> Mensagem :{}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT Claims string está vazia -> Mensagem : {}", e);
        }
        return false;
    }
}