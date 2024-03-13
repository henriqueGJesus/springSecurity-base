package usuario.com.usuario.config;

import lombok.AllArgsConstructor;
import org.hibernate.query.sqm.internal.NoParamSqmCopyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import usuario.com.usuario.service.AutenticacaoService;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfigs {

    private final AutenticacaoService autenticacaoService;

//    @Autowired
//    public void config(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(autenticacaoService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(autenticacaoService);
        return new ProviderManager(dao);
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration= new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8082)"));
        corsConfiguration.setAllowedMethods(List.of("POST","GET","PUT","DELETE","PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource corsConfigurationSource=
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return corsConfigurationSource;
    }
}
