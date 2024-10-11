package br.com.kraken.Produto.java.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table (name = "Produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 10)
    private UUID identificador;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "data_recebimento", nullable = false)
    private LocalDateTime dataRecebimento;
    @Column(name = "tipo", nullable = false)
    private boolean tipo;
    @Column(name = "valor_unitario", nullable = false)
    private float valorUnitario;

    public ProdutoModel(UUID identificador, String nome, String descricao, LocalDateTime dataRecebimento, boolean tipo, float valorUnitario) {
        this.identificador = identificador;
        this.nome = nome;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.tipo = tipo;
        this.valorUnitario = valorUnitario;
    }

    public ProdutoModel () {}

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
