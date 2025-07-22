package org.example.ChatPesquisa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PerguntaDTO;
import org.example.entities.PesquisaResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/pesquisa")
@CrossOrigin(origins = "http://localhost:4200")
public class Pesquisa {

    private final String COHERE_API_KEY = "KrS0orfKe6dsPPJShaMayrb8pv8PiCE2r3QZE8Hh"; // 🔒 substitua pela sua chave real
    private final String COHERE_API_URL = "https://api.cohere.ai/v1/generate";

    @PostMapping
    public ResponseEntity<PesquisaResponse> pesquisar(@RequestBody PerguntaDTO perguntaDTO) {
        String pergunta = perguntaDTO.getPergunta();

        if (pergunta == null || pergunta.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new PesquisaResponse("Pergunta não pode ser vazia", null));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + COHERE_API_KEY);

        String bodyJson = String.format(
                "{\n" +
                        "  \"model\": \"command-r\",\n" +
                        "  \"prompt\": \"%s\",\n" +
                        "  \"max_tokens\": 300,\n" +
                        "  \"temperature\": 0.7\n" +
                        "}", pergunta.replace("\"", "\\\"")
        );

        HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        PesquisaResponse response = new PesquisaResponse();

        try {
            ResponseEntity<String> result = restTemplate.postForEntity(COHERE_API_URL, request, String.class);
            System.out.println("Resposta da Cohere: " + result.getBody());

            if (result.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(result.getBody());
                JsonNode generations = root.path("generations");

                if (generations.isArray() && generations.size() > 0) {
                    String respostaIA = generations.get(0).path("text").asText();
                    response.setAnswer(respostaIA);
                    response.setAbstractText(null);
                    return ResponseEntity.ok(response);
                } else {
                    response.setAnswer("Nenhuma resposta gerada pela API");
                    response.setAbstractText(null);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.setAnswer("Erro na API: " + result.getStatusCode());
                response.setAbstractText(null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.setAnswer("Erro ao acessar a API: " + e.getMessage());
            response.setAbstractText(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
