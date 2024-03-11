package usuario.com.usuario.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usuario.com.usuario.model.dto.UsuarioLogin;
import usuario.com.usuario.utils.CookieUtil;
import usuario.com.usuario.utils.JwtUtil;

@RestController
@AllArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private JwtUtil jwtUtil = new JwtUtil();
    private CookieUtil cookieUtil = new CookieUtil();
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(
            @RequestBody UsuarioLogin usuarioLogin, HttpServletRequest request, HttpServletResponse response){
        //Tentando fazer a autenticação
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioLogin.getUsername(), usuarioLogin.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

//            //Criando um contexto de segurança(Contexto é onde se guarda o usuario já autenticado/ Manter o usuario ativo)
//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            //contexto setando a autenticação
//            context.setAuthentication(authentication);
//            //salvando o contexto
//            securityContextRepository.saveContext(context,request,response);

            //Gera o cookie depois de autenticar o usuario
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String token = jwtUtil.gerarToken(userDetails);
            Cookie cookie = cookieUtil.gerarCookieJwt(userDetails);
            response.addCookie(cookie);

            return ResponseEntity.ok("Autenticação bem-sucedida");
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }
}
