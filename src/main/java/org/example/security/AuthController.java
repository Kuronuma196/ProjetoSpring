package org.example.security;

import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.services.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

        Usuario usuario = usuarioRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtUtil.gerarToken(userDetails, usuario.getTipo());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/dados-funcionario")
    public ResponseEntity<String> dadosFuncionario(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String tipo = jwtUtil.extrairTipo(token);

        if (!tipo.equals("FUNCIONARIO")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado.");
        }

        return ResponseEntity.ok("Dados visíveis para FUNCIONÁRIO.");
    }

}


