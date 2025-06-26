package br.com.kraken.Produto.java.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoModelDTO(String nomeProduto, String descricao, LocalDateTime dataModificacao, UUID categoriaId, float valorUnitario, UUID estoqueId, UUID clienteId, int quantidadeProduto, String image) {
    
}
