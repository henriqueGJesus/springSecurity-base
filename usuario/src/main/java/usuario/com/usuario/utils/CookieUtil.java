package usuario.com.usuario.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
@Component
public class CookieUtil {
    public Cookie gerarCookieJwt(UserDetails userDetails){
        String token = new JwtUtil().gerarToken(userDetails);
        Cookie cookie = new Cookie("JWT",token);
        cookie.setPath("/");
        cookie.setMaxAge(3000);
        return cookie;
    }
    public Cookie getCookie(HttpServletRequest request, String name){
        return WebUtils.getCookie(request,name);

    }
}
