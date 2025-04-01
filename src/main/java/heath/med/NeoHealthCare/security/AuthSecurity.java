package heath.med.NeoHealthCare.security;

import heath.med.NeoHealthCare.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthSecurity extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //Recupera o token do cabeçalho.
    var tokenJWT = recuperarToken(request);

    // entra neste trexo caso o usuario autenticou previamente
    if (tokenJWT != null) {
      var subject = tokenService.getSubject(tokenJWT);
      var user = usuarioRepository.findByLogin(subject);

      //Autentica o usuario
      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

      System.out.println("logado");
    }

    //Necessário para chamar o próximo filtro na aplicação.
    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null) {
      return null;
    }
    return authorizationHeader.replace("Bearer ", "");

  }
}