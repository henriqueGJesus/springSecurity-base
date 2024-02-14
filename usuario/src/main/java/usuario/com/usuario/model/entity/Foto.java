package usuario.com.usuario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foto {

    public Foto(MultipartFile foto) throws IOException {
        this.nome = foto.getOriginalFilename();
        this.dados = foto.getBytes();
        this.tipo = foto.getContentType();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String tipo;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dados;
}
