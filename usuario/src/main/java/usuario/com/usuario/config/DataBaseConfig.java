package usuario.com.usuario.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import usuario.com.usuario.Autorizacao;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.model.entity.UsuarioDetailsEntity;
import usuario.com.usuario.repository.UsuarioRepository;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {
    private final UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init(){
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setUsuarioDetailsEntity(
                UsuarioDetailsEntity.builder()
                        .usuario(usuario)
                        .enabled(true)
                        .AccountNonExpired(true)
                        .AccountNonLocked(true)
                        .CredentialsNonExpired(true)
                        .username("teste")
                        .password(new BCryptPasswordEncoder().encode("teste123"))
                        .authorities(List.of(Autorizacao.GET))
                        .build());
        usuarioRepository.save(usuario);
    }
}
