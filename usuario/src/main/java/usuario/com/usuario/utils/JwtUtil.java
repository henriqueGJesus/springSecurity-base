package usuario.com.usuario.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtUtil {

    private final SecretKey key;

    public JwtUtil(){
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String senha= passwordEncoder.encode("senha123");
        this.key= Keys.hmacShaKeyFor(senha.getBytes());
    }
        public String gerarToken(UserDetails userDetails){

            return Jwts.builder().issuer("OG").
                    issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 300000))
                    .signWith(this.key, Jwts.SIG.HS256)
                    .subject(userDetails.getUsername())
                    .compact();
        }

        private Jws<Claims> validarToken(String token){
            return getParser().parseSignedClaims(token);
        }

        public String getUsername(String token){
            return validarToken(token).getPayload().getSubject();
        }

        private JwtParser getParser(){
            return Jwts.parser()
                    .verifyWith(this.key)
                    .build();
        }
}
