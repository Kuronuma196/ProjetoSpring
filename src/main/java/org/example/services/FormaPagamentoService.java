package org.example.services;

import org.example.entities.FormaPagamento;
import org.example.repositories.FormaPagamentoRepository;
import org.example.resources.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> getAll() {
        return repository.findAll();
    }

    public FormaPagamento findById(Long id) {
        Optional<FormaPagamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public FormaPagamento insert(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public boolean update(Long id, FormaPagamento formaPagamento) {
        Optional<FormaPagamento> optionalFormaPagamento = repository.findById(id);
        if (optionalFormaPagamento.isPresent()) {
            FormaPagamento formaPagamentoSistema = optionalFormaPagamento.get();
            formaPagamentoSistema.setFpgNome(formaPagamento.getFpgNome());
            formaPagamentoSistema.setFpgNome(formaPagamento.getFpgNome());
            formaPagamentoSistema.setFpgPrice(formaPagamento.getFpgPrice());
            formaPagamentoSistema.setAtivo(formaPagamento.getAtivo());
            formaPagamentoSistema.setPermiteParcelamento(formaPagamento.getPermiteParcelamento());
            formaPagamentoSistema.setNumeroMaximoParcelas(formaPagamento.getNumeroMaximoParcelas());
            formaPagamentoSistema.setTaxaAdicional(formaPagamento.getTaxaAdicional());
            repository.save(formaPagamentoSistema);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
