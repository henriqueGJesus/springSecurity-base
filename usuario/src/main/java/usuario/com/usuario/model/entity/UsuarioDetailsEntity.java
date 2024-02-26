package usuario.com.usuario.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import usuario.com.usuario.Autorizacao;
import usuario.com.usuario.model.entity.Usuario;

import java.util.Collection;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class UsuarioDetailsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Usuario usuario;
    private String password;
    @Column(unique = true,nullable = false,updatable = false)
    private String username;
    private boolean enabled;
    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private Collection<Autorizacao> authorities;

}
