package org.example.ChatPesquisa;

import dto.PerguntaDTO;
import org.example.services.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pesquisa")
@CrossOrigin(origins = "http://localhost:4200")
public class Pesquisa {

    @Autowired
    private PesquisaService pesquisaService;

   @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PesquisaResponse> pesquisar(@RequestBody PerguntaDTO dto) {
        if (dto.getPergunta() == null || dto.getPergunta().isBlank()) {
            return ResponseEntity.badRequest().body(
                    new PesquisaResponse("Pergunta não fornecida.", null)
            );
        }

        var resultado = pesquisaService.pesquisar(dto.getPergunta());
        String resposta = resultado.get("resposta");

        return ResponseEntity.ok(new PesquisaResponse(resposta, null));
    }
}
