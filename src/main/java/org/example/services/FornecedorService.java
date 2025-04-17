package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repositories.EnderecoRepository;
import org.example.repositories.FornecedorRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Pageable pageable;

    public List<Fornecedor> findAll() {

        return repository.findAll();
    }

    public Page<Fornecedor> getAllFornecedorsPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Fornecedor insert(Fornecedor obj) {
        try {
            obj.setForId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }

    }

    public Fornecedor update(Long id, FornecedorDTO objDto) {
        try {
            Fornecedor entity = findById(id);

            // Atualiza os dados do fornecedor
            entity.setForRazaoSocial(objDto.getForRazaoSocial());
            entity.setForNomeFantasia(objDto.getForNomeFantasia());
            entity.setForCnpj(objDto.getForCnpj());
            entity.setForFlag(objDto.getForFlag());
            entity.setForAnotacao(objDto.getForAnotacao());

            // Atualiza o endereço do fornecedor
            Endereco endereco = entity.getEnderecos().get(0); // Assumindo que há apenas um endereço por cliente
            endereco.setEndRua(objDto.getEndRua());
            endereco.setEndNumero(objDto.getEndNumero());
            endereco.setEndBairro(objDto.getEndBairro());
            endereco.setEndCidade(objDto.getEndCidade());
            endereco.setEndCep(objDto.getEndCep());
            endereco.setEndEstado(objDto.getEndEstado());
            endereco.setEndComplemento(objDto.getEndComplemento());

            // Atualiza o contato
            Contato contato = entity.getContatos().get(0); // Assumindo que há apenas um contato por cliente
            contato.setConCelular(objDto.getConCelular());
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConEmail(objDto.getConEmail());
            contato.setConEmailSecundario(objDto.getConEmailSecundario());

            // Salva as alterações
            repository.save(entity);

            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }

    }

    public void deleteFornecedor(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Fornecedor fromDTO(FornecedorDTO objDto) {
        Fornecedor fornec = new Fornecedor(null, objDto.getForRazaoSocial(), objDto.getForNomeFantasia(),
                objDto.getForCnpj(), objDto.getForAnotacao());

        Endereco ender = new Endereco(null, fornec, objDto.getEndRua(), objDto.getEndNumero(),
                objDto.getEndBairro(), objDto.getEndCidade(), objDto.getEndCep(),
                objDto.getEndEstado(), objDto.getEndComplemento());

        Contato contato = new Contato(null, fornec,  objDto.getConCelular(), objDto.getConTelefoneComercial(),
                objDto.getConEmail(), objDto.getConEmailSecundario());

        fornec.getEnderecos().add(ender);
        fornec.getContatos().add(contato);

        return fornec;
    }

    public FornecedorDTO toNewDTO(Fornecedor obj) {
        FornecedorDTO dto = new FornecedorDTO();

        // Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
        dto.setForId(obj.getForId());
        dto.setForRazaoSocial(obj.getForRazaoSocial());
        dto.setForNomeFantasia(obj.getForNomeFantasia());
        dto.setForCnpj(obj.getForCnpj());
        dto.setForFlag(obj.getForFlag());
        dto.setForAnotacao(obj.getForAnotacao());

        // Atributos específicos de Endereco
        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndBairro(endereco.getEndBairro());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());
        dto.setEndComplemento(endereco.getEndComplemento());

        // Atributos específicos de Contato
        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());
        dto.setConEmailSecundario(contato.getConEmailSecundario());

        return dto;
    }

}