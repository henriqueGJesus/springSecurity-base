package usuario.com.usuario.model.dto;

import lombok.*;
import usuario.com.usuario.model.entity.Foto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEdicaoDTO implements IDTO{

    private Integer id;
    private String nome;
    private String senha;
    private String email;
    private Boolean status;
    private Integer idade;
    private Foto foto;
}
