package usuario.com.usuario.controller;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.model.entity.UsuarioDetailsEntity;
import usuario.com.usuario.repository.UsuarioRepository;
import usuario.com.usuario.service.AutenticacaoService;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        //Prevenção de ataque através de um token/ cria um token para poder identificar o usuario
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(HttpMethod.POST,"/usuario").permitAll()
                .requestMatchers(HttpMethod.GET,"/usuario").hasAuthority("GET")
                .requestMatchers(HttpMethod.PUT,"/usuario").permitAll()
                .anyRequest().authenticated());
        http.securityContext((context)-> context.securityContextRepository(securityContextRepository));
        http.formLogin(Customizer.withDefaults());
        http.logout((Customizer.withDefaults()));
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return autenticacaoService;
//    }

//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    public UserDetailsManager inMemoryUser(){
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withDefaultPasswordEncoder().username("mi72").password("M!7dois").build();
//        return new InMemoryUserDetailsManager(user);
//    }
}
