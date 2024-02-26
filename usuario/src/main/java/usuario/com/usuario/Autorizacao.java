package usuario.com.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Locale;

@AllArgsConstructor
public enum Autorizacao implements GrantedAuthority {


    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    public static Autorizacao getAutorizacao(String nome) {
        return valueOf(nome.toUpperCase());

    }
    private final String name;
    @Override
    public String getAuthority() {
        return name();
    }
}
