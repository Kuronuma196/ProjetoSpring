package org.example.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PesquisaService {

    @Value("${cohere.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> pesquisar(String pergunta) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            String bodyJson = String.format(
                    "{\n" +
                            "  \"model\": \"command\",\n" +
                            "  \"prompt\": \"Você é Ângela, uma assistente inteligente do sistema Panema SENAI. Responda APENAS em português, de forma clara e útil para funcionários e donos de microempresas de padaria. Pergunta: %s\",\n" +
                            "  \"temperature\": 0.7,\n" +
                            "  \"max_tokens\": 500,\n" +
                            "  \"stop_sequences\": [\"--\"]\n" +
                            "}", pergunta.replace("\"", "\\\"")
            );



            HttpEntity<String> entity = new HttpEntity<>(bodyJson, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://api.cohere.ai/v1/generate",
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                String resposta = jsonNode.get("generations").get(0).get("text").asText();
                return Map.of("resposta", resposta.trim());
            } else {
                return Map.of("resposta", "Erro na chamada à API da Cohere: " + response.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("resposta", "Erro ao acessar a API: " + e.getMessage());
        }
    }
}
