package ru.urlshortcut.jwtfilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.urlshortcut.model.Site;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ru.urlshortcut.jwtfilter.JWTConstants.SECRET;
import static ru.urlshortcut.jwtfilter.JWTConstants.EXPIRATION_TIME;

/**
 * Фильтр аутентификации(проверка логина и пароля) который отлавливает пользователя.
 * Метод attemptAuthentication проверяет, что логин и пароль верные.
 * Метод successfulAuthentication генерирует token.
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager auth;

    public JWTAuthenticationFilter(AuthenticationManager auth) {
        this.auth = auth;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            Site site = new ObjectMapper().readValue(req.getInputStream(), Site.class);
            return auth.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            site.getLogin(),
                            site.getPassword(),
                            null)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.getWriter().println(token);
    }
}
