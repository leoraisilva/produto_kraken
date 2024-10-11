package br.com.kraken.Produto.java.service;

import br.com.kraken.Produto.java.model.ProdutoModel;
import br.com.kraken.Produto.java.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService  {
    private final ProdutoRepository produtoRepository;

    public ProdutoService (ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }


}
