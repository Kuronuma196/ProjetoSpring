package org.example.security;

import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.services.UsuarioDetailsService;
import org.example.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import dto.UsuarioBaseDTO;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:4200") // ajuste se necessário
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioDetailsService userDetailsService;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getSenha()
                    )
            );

            Usuario usuario = usuarioRepo.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtil.gerarToken(userDetails, usuario.getTipo());

            return ResponseEntity.ok(new TokenResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", "Email ou senha inválidos."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("erro", "Erro interno no servidor"));
        }
    }

    @PostMapping("/cadastro")
public ResponseEntity<?> cadastro(@RequestBody UsuarioBaseDTO novoUsuarioDTO) {
    if (usuarioRepo.findByEmail(novoUsuarioDTO.getEmail()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("erro", "Email já cadastrado."));
    }

    try {
        Usuario criado = usuarioService.criarOuAtualizarUsuario(novoUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("erro", e.getMessage()));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("erro", "Erro interno no servidor"));
    }
}


    @GetMapping("/dados-funcionario")
    public ResponseEntity<?> dadosFuncionario(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String tipo = jwtUtil.extrairTipo(token);

        if (!tipo.equals("FUNCIONARIO")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("erro", "Acesso negado."));
        }

        return ResponseEntity.ok(Collections.singletonMap("mensagem", "Dados visíveis para FUNCIONÁRIO."));
    }
}
