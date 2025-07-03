package org.example.ChatPesquisa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PerguntaDTO;
import org.example.entities.PesquisaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/pesquisa")
@CrossOrigin(origins = "http://localhost:4200") // <–– adiciona esta linha
public class pesquisa {
    @PostMapping
    public PesquisaResponse pesquisar(@RequestBody PerguntaDTO perguntaDTO) {
        String pergunta = perguntaDTO.getPergunta();

        String url = "https://api.duckduckgo.com/?q=" + pergunta + "&format=json&no_redirect=1&no_html=1";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);

        PesquisaResponse response = new PesquisaResponse();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(resposta.getBody());

            String abstractText = json.path("Abstract").asText();
            String answer = json.path("Answer").asText();

            if (abstractText.isEmpty() && answer.isEmpty()) {
                answer = "Nenhuma resposta encontrada na web.";
            }

            response.setAbstractText(abstractText);
            response.setAnswer(answer);

        } catch (Exception e) {
            response.setAnswer("Erro ao processar resposta: " + e.getMessage());
        }

        return response;
    }
}
