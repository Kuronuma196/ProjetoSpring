package org.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "segredo-super-seguro";
    private final long expiracao = 1000 * 60 * 60 * 10; // 10h

    public String gerarToken(UserDetails userDetails, TipoUsuario tipo) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("tipo", tipo.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracao))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String extrairTipo(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("tipo", String.class);
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        final String email = extrairEmail(token);
        return email.equals(userDetails.getUsername()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        Date exp = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return exp.before(new Date());
    }
}

