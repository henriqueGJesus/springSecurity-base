package usuario.com.usuario.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import usuario.com.usuario.model.entity.Foto;

import java.io.IOException;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UsuarioCadastroDTO implements IDTO {
     @NonNull
     private String nome;
     private String senha;
     @NonNull
     private String email;
     private Boolean status;
     @NonNull
     private Integer idade;
     private Foto foto;

     public void setFoto(MultipartFile foto) throws IOException {
          Foto a = new Foto();
          a.setDados(foto.getBytes());
          a.setNome(foto.getOriginalFilename());
          a.setTipo(foto.getContentType());
          this.foto = a;
     }
}
