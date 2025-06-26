package org.example.ChatPesquisa;
import dto.PerguntaDTO;
import org.example.entities.PesquisaResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/pesquisa")
public class pesquisa {
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<?> pesquisar(@RequestBody PerguntaDTO dto) {
        if (dto.getPergunta() == null || dto.getPergunta().isBlank())
            return ResponseEntity.badRequest().body("Pergunta não fornecida");

        String query = dto.getPergunta().replaceAll(" ", "+");
        String url = "https://api.duckduckgo.com/?q=" + query + "&format=json&no_redirect=1";

        try {
            PesquisaResponse resp = restTemplate.getForObject(url, PesquisaResponse.class);
            String resposta = resp.getAbstractText() != null && !resp.getAbstractText().isEmpty()
                    ? resp.getAbstractText()
                    : (resp.getAnswer() != null ? resp.getAnswer() : "Sem resposta clara.");

            return ResponseEntity.ok(Map.of("resposta", resposta));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar dados externos.");
        }
    }
}
