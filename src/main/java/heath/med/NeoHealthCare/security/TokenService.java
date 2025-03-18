package heath.med.NeoHealthCare.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import heath.med.NeoHealthCare.domain.usuario.Usuario;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@Log4j2
public class TokenService {

  public String acessoToken(Usuario usuario) {
    try {
      Algorithm algrt = Algorithm.HMAC256("123456");
      return JWT.create()
          .withIssuer("NeoHealthCare")
          .withSubject(usuario.getLogin())
          .withExpiresAt(expireToken())
          .sign(algrt);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token{}", exception);
    }
  }

  private Instant expireToken() {
    return LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00"));
  }
}
