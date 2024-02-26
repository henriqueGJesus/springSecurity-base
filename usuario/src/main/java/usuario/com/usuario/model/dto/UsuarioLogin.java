package usuario.com.usuario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioLogin {
    private String username;
    private String password;
}
