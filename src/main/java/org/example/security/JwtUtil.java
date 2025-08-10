package org.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your-secret-key-here-1234567890";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
            .setSubject(usuario.getEmail())
            .claim("userId", usuario.getId())
            .claim("tipo", usuario.getTipo())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }

    public boolean validarToken(String token, Usuario usuario) {
        String email = extrairEmail(token);
        return email.equals(usuario.getEmail()) && !tokenExpirado(token);
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String extrairTipo(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .get("tipo", String.class);
    }

    private boolean tokenExpirado(String token) {
        Date expiration = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
        return expiration.before(new Date());
    }

    private final String secret = "segredo-super-seguro";
    private final long expiracao = 1000 * 60 * 60 * 10; // 10h

   

  

    public Long extrairId(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("id", Long.class);
    }

    

    public boolean validarToken(String token, UserDetails userDetails) {
        try {
            final String email = extrairEmail(token);
            return (email.equals(userDetails.getUsername()) && !estaExpirado(token));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean estaExpirado(String token) {
        Date exp = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return exp.before(new Date());
    }
}