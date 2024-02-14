package usuario.com.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usuario.com.usuario.model.entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
