package org.example.services;
import dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.repositories.ClienteRepository;
import org.example.repositories.EnderecoRepository;
import org.example.resources.exeptions.ResourceNotFoundException;
import org.example.resources.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }
    public Cliente findById(Long id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
public Cliente insert(Cliente obj){
        try{
            obj.setCliId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
}
    public Cliente update(Long id, ClienteDTO objDto){
        try {
            Cliente entity = findById(id);
            entity.setCliNome(objDto.getCliNome());
            entity.setCliCpf(objDto.getCliCpf());

            Endereco endereco = entity.getEnderecos().get(0);

            endereco.setEndRua(objDto.getEndRua());
            endereco.setEndNumero(objDto.getEndNumero());
            endereco.setEndCidade(objDto.getEndCidade());
            endereco.setEndCep(objDto.getEndCep());
            endereco.setEndEstado(objDto.getEndEstado());

            Contato contato = entity.getConetatos().get(0);

            contato.setConCelular(objDto.getConCelular());
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConEmail(objDto.getConEmail());

            repository.save(entity);
            return entity;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        Cliente fornec = new Cliente(null, objDto.getCliNome(), objDto.getCliCpf());

        Endereco ender = new Endereco(null, fornec, objDto.getEndRua(), objDto.getEndNumero(),
                objDto.getEndCidade(), objDto.getEndCep(),
                objDto.getEndEstado());

        Contato contato = new Contato(null, fornec, objDto.getConCelular(), objDto.getConTelefoneComercial(),
                objDto.getConEmail());

        fornec.getEnderecos().add(ender);
        fornec.getConetatos().add(contato);

        return fornec;
    }

    public ClienteDTO toNewDTO(Cliente obj) {
        ClienteDTO dto = new ClienteDTO();

// Mapeie os atributos comuns entre Cliente e ClienteNewDTO
        dto.setCliId(obj.getCliId());
        dto.setCliNome(obj.getCliNome());
        dto.setCliCpf(obj.getCliCpf());

// Atributos específicos de Endereco
        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

// Atributos específicos de Contato
        Contato contato = obj.getConetatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }


    public boolean update(Long id, Cliente cliente) {
        return false;
    }

}
