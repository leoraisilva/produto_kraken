package br.com.kraken.Produto.java.dto;

import java.time.LocalDateTime;

public record ProdutoModelDTO(String nome, String descricao, LocalDateTime dataRecebimento, boolean tipo, float valorUnitario) {
    
}
