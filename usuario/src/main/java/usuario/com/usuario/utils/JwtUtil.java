package usuario.com.usuario.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

    public class JwtUtil {
        public String gerarToken(UserDetails userDetails){
            return Jwts.builder().issuer("OG").issuedAt(new Date()).expiration(new Date(new Date().getTime() + 300000))
                    .signWith(SignatureAlgorithm.NONE,"senha123").subject(userDetails.getUsername()).compact();
        }

        private Jws<Claims> validarToken(String token){
            return getParser().parseSignedClaims(token);
        }

        public String getUsername(String token){
            return validarToken(token).getPayload().getSubject();
        }

        private JwtParser getParser(){
            return Jwts.parser().setSigningKey("senha123").build();
        }
}
