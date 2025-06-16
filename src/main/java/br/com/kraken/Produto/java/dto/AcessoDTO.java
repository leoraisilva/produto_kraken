package br.com.kraken.Produto.java.dto;

import br.com.kraken.Produto.java.model.Rules;

import java.time.LocalDateTime;

public record AcessoDTO(String usuarioId, String usuario, LocalDateTime dataCadastro, Rules rules) {
}
