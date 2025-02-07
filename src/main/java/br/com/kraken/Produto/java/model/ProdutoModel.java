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
    private UUID produtoId;
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "data_modificacao", nullable = false)
    private LocalDateTime dataModificacao;
    @Column(name = "categoriaId", nullable = false)
    private UUID categoriaId;
    @Column(name = "valor_unitario", nullable = false)
    private float valorUnitario;
    @Column(name = "estoqueId", nullable = false)
    private UUID estoqueId;
    @Column(name = "quantidade_produto")
    private int quantidadeProduto;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    public ProdutoModel(String nomeProduto, String descricao, LocalDateTime dataModificacao, UUID categoriaId, float valorUnitario, UUID estoqueId, int quantidadeProduto, String image) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.dataModificacao = dataModificacao;
        this.categoriaId = categoriaId;
        this.valorUnitario = valorUnitario;
        this.estoqueId = estoqueId;
        this.quantidadeProduto = quantidadeProduto;
        this.image = image;
    }

    public ProdutoModel () {}

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public UUID getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(UUID estoqueId) {
        this.estoqueId = estoqueId;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
