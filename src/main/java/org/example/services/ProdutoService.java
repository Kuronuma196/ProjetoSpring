package org.example.services;

import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.example.resources.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Produto insert(Produto produto) {
        return repository.save(produto);
    }

    public boolean update(Long id, Produto produto) {
        Optional<Produto> optionalProduto = repository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produtoSistema = optionalProduto.get();
            produtoSistema.setProFornecedor(produto.getProFornecedor());
            produtoSistema.setProNome(produto.getProNome());
            produtoSistema.setProPrecoCusto(produto.getProPrecoCusto());
            produtoSistema.setProPrecoVenda(produto.getProPrecoVenda());
            produtoSistema.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoSistema.setCategoria(produto.getCategoria());
            produtoSistema.setCodigoBarras(produto.getCodigoBarras());
            produtoSistema.setMarca(produto.getMarca());
            produtoSistema.setUnidadeMedida(produto.getUnidadeMedida());
            produtoSistema.setAtivo(produto.getAtivo());
            produtoSistema.setDataCadastro(produto.getDataCadastro());
            produtoSistema.setDataAtualizacao(produto.getDataAtualizacao());
            repository.save(produtoSistema);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
