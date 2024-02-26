package usuario.com.usuario.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usuario.com.usuario.model.dto.UsuarioCadastroDTO;
import usuario.com.usuario.model.dto.UsuarioEdicaoDTO;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;
import usuario.com.usuario.service.UsuarioService;

import java.io.IOException;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping("/id")
    public ResponseEntity<Usuario> buscarUm(@RequestParam Integer id){
        try {
            return new ResponseEntity<>(usuarioService.buscarUm(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<Collection<Usuario>> buscarTodos(){
        try{
            return new ResponseEntity<>(usuarioService.buscarTodos(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> editar(@RequestBody UsuarioEdicaoDTO usuarioEdicaoDTO){
       return new ResponseEntity<>(usuarioService.editar(usuarioEdicaoDTO),HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO){

        return new ResponseEntity<>(usuarioService.cadastrarSemFoto(usuarioCadastroDTO),HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Usuario> editaPatch(@RequestBody UsuarioEdicaoDTO usuarioEdicaoDTO) throws Exception {
        return new ResponseEntity<>(usuarioService.editarPatch(usuarioEdicaoDTO),HttpStatus.OK)  ;
    }

    @PatchMapping("/senha/{id}")
    public void editarSenha(@PathVariable Integer id, @RequestParam String senha){
        usuarioService.editarSenha(id,senha);
    }

    @PatchMapping("/status/{id}")
    public void editarStatus(@PathVariable Integer id){
        usuarioService.editarStatus(id);
    }

    @PatchMapping("/{id}")
    public void cadastrarFoto(@RequestParam MultipartFile foto, @PathVariable Integer id) throws IOException {
        usuarioService.atualizarFoto(id,foto);
    }

}
