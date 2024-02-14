package usuario.com.usuario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private String email;
    private Boolean status;
    private Integer idade;
    @OneToOne(cascade = CascadeType.ALL)
    private Foto foto;
}
