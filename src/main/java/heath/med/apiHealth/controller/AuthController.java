package heath.med.apiHealth.controller;

import heath.med.apiHealth.domain.usuario.DadosAutenticacao;
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

  @PostMapping("/token")
  public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
    var token = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
    Authentication authenticate = manager.authenticate(token);
    return ResponseEntity.ok().build();
  }

//  @PostMapping("/auth")
//  public ResponseEntity auth(@RequestBody @Valid DadosAutenticacao dados){
//
//  }
}
