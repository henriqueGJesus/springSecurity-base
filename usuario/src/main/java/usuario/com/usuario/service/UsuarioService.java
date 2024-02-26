package usuario.com.usuario.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import usuario.com.usuario.model.dto.IDTO;
import usuario.com.usuario.model.dto.UsuarioCadastroDTO;
import usuario.com.usuario.model.dto.UsuarioEdicaoDTO;
import usuario.com.usuario.model.entity.Foto;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ObjectMapper objectMapper;
    private ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
 }

    public Usuario buscarUm(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario cadastrarSemFoto(IDTO idto){
        UsuarioCadastroDTO usuarioCadastroDTO = (UsuarioCadastroDTO) idto;
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO,usuario);
       return usuarioRepository.save(usuario);
    }

    public void atualizarFoto(Integer id, MultipartFile foto) throws IOException {
           Usuario usuario = buscarUm(id);
           usuario.setFoto(new Foto(foto));
           usuarioRepository.save(usuario);
    }

    public Usuario editar(IDTO idto){
        UsuarioEdicaoDTO usuarioEdicaoDTO = (UsuarioEdicaoDTO) idto;
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioEdicaoDTO , usuario);
       return usuarioRepository.save(usuario);
    }

    public Usuario editarPatch(IDTO idto){
       UsuarioEdicaoDTO usuarioEdicaoDTO = (UsuarioEdicaoDTO) idto;
       Usuario usuario;
       usuario = usuarioRepository.findById(usuarioEdicaoDTO.getId()).get();
       modelMapper.map(usuarioEdicaoDTO,usuario);
     return  usuarioRepository.save(usuario);

    }

    public void editarSenha(Integer id, String senha){
        Usuario usuario = buscarUm(id);
//        usuario.setSenha(senha);
        usuarioRepository.save(usuario);

    }

    public void editarStatus(Integer id){
        Usuario usuario = buscarUm(id);
        usuario.setStatus(!usuario.getStatus());
        usuarioRepository.save(usuario);
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
