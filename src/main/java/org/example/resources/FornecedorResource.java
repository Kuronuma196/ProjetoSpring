package org.example.resources;
import dto.ClienteDTO;
import dto.FornecedorDTO;
import org.example.entities.Cliente;
import org.example.entities.Fornecedor;
import org.example.entities.Produto;
import org.example.services.FornecedorService;
import org.example.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> findAll(){
        List<Fornecedor> list = fornecedorService.findAll();
        List<FornecedorDTO> listDto = list.stream().map(obj -> fornecedorService.toNewDTO(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id){
        Fornecedor obj = fornecedorService.findById(id);
        FornecedorDTO dto = fornecedorService.toNewDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody FornecedorDTO dto){
        Fornecedor obj = fornecedorService.fromDTO(dto);
        obj = fornecedorService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getForId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody FornecedorDTO objDto, @PathVariable Long id){
        fornecedorService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id){
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
