package org.example.security;

import dto.UsuarioBaseDTO;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.services.AuthService;
import org.example.services.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UsuarioRepository usuarioRepo;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(
        AuthenticationManager authManager,
        UsuarioRepository usuarioRepo,
        UsuarioService usuarioService,
        JwtUtil jwtUtil,
        AuthService authService
    ) {
        this.authManager = authManager;
        this.usuarioRepo = usuarioRepo;
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());
        try {
            Usuario usuario = authService.validateCredentials(request.getEmail(), request.getSenha());
            
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getSenha()
                )
            );
            
            String token = jwtUtil.gerarToken(usuario);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", usuario.getId());
            response.put("userType", usuario.getTipo());
            response.put("email", usuario.getEmail());
            
            return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(response);
                
        } catch (BadCredentialsException ex) {
            log.error("Invalid credentials for email: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("erro", "Credenciais inválidas"));
        } catch (Exception ex) {
            log.error("Login error for email: {}", request.getEmail(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("erro", "Erro durante o login"));
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody UsuarioBaseDTO novoUsuarioDTO) {
        log.info("Registration attempt for email: {}", novoUsuarioDTO.getEmail());
        if (usuarioRepo.findByEmail(novoUsuarioDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("erro", "Email já cadastrado"));
        }

        try {
            Usuario criado = usuarioService.criarOuAtualizarUsuario(novoUsuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (Exception e) {
            log.error("Registration error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("erro", "Erro ao criar usuário"));
        }
    }
}