package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<Fornecedor> getAll() {
        return repository.findAll();
    }

    public Page<Fornecedor> getAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Fornecedor> findById(Long id) {
        return repository.findById(id);
    }

    public Fornecedor insert(Fornecedor cargaHoraria) {
        return repository.save(cargaHoraria);
    }

    public boolean update(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> optionalFornecedor = repository.findById(id);
        if (optionalFornecedor.isPresent()) {
            Fornecedor existFornecedor = optionalFornecedor.get();
            existFornecedor.setNome(fornecedor.getNome());
            repository.save(fornecedor);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
