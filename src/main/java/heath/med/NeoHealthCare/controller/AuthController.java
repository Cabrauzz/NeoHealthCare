package heath.med.NeoHealthCare.controller;

import heath.med.NeoHealthCare.domain.usuario.DadosAutenticacao;
import heath.med.NeoHealthCare.domain.usuario.Usuario;
import heath.med.NeoHealthCare.security.DadosTokenJWT;
import heath.med.NeoHealthCare.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @PostMapping()
  public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
    var token = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
    Authentication authenticate = manager.authenticate(token);
    String authJWT = tokenService.acessoToken((Usuario) authenticate.getPrincipal());
    return ResponseEntity.ok(new DadosTokenJWT(authJWT));
  }

}
