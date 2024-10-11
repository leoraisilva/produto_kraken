package br.com.kraken.Produto.java.repository;

import br.com.kraken.Produto.java.model.ProdutoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoModel, UUID> {
    List<ProdutoModel> findAll();
}
